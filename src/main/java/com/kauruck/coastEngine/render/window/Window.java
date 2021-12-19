package com.kauruck.coastEngine.render.window;

import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.input.KeyCode;
import com.kauruck.coastEngine.render.input.KeyHelper;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final String title;

    private static long id;

    private static boolean inited = false;

    private static int pid = 0;

    private static boolean resized = false;

    private static int width = 0;

    private static int height = 0;

    private Window(String title) throws NoSuchProcessException {
        if(Window.inited)
            throw new IllegalStateException("The max. amount of windows is 1");
        this.title = title;

        this.init();
    }
    public String getTitle() {
        return title;
    }

    public static long getId() {
        return id;
    }

    public static boolean isInited() {
        return inited;
    }

    public static int getPid() {
        return pid;
    }

    public static void createWindow(String title){
        try {
            new Window(title);
        } catch (NoSuchProcessException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        id = glfwCreateWindow(300, 300, title, NULL, NULL);
        if ( id == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(id, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
            KeyCode code = KeyHelper.parseKeyCode(key);
            if(code != KeyCode.None){
                if(action == GLFW_PRESS) {
                    Input.onKeyDown(code);
                }else if(action == GLFW_RELEASE){
                    Input.onKeyUp(code);
                }
            }
        });

        glfwSetFramebufferSizeCallback(id, (window, nWidth, nHeight) -> {
            width = nWidth;
            height = nHeight;
            resized = true;
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(id, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    id,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(id);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(id);
        //Release the context so that the render threads can use it
        glfwMakeContextCurrent(NULL);
    }

    public static boolean isResized() {
        return resized;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setResized(boolean resized) {
        Window.resized = resized;
    }
}
