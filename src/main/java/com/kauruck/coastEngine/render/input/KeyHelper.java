package com.kauruck.coastEngine.render.input;

import com.kauruck.coastEngine.core.input.KeyCode;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHelper {

    /**
     * List of not supported keys (yet):
     * - Alt Gr (will trigger left crt + alt)
     * - pos1
     * - dash
     * - hash
     * @param keyCode The GLFW KeyCode
     * @return The coastEngine KeyCode
     */
    public static KeyCode parseKeyCode(int keyCode){
        return switch (keyCode){
            //Letters
            case GLFW_KEY_A -> KeyCode.A;
            case GLFW_KEY_B -> KeyCode.B;
            case GLFW_KEY_C -> KeyCode.C;
            case GLFW_KEY_D -> KeyCode.D;
            case GLFW_KEY_E -> KeyCode.E;
            case GLFW_KEY_F -> KeyCode.F;
            case GLFW_KEY_G -> KeyCode.G;
            case GLFW_KEY_H -> KeyCode.H;
            case GLFW_KEY_I -> KeyCode.I;
            case GLFW_KEY_J -> KeyCode.J;
            case GLFW_KEY_K -> KeyCode.K;
            case GLFW_KEY_L -> KeyCode.L;
            case GLFW_KEY_M -> KeyCode.M;
            case GLFW_KEY_N -> KeyCode.N;
            case GLFW_KEY_O -> KeyCode.O;
            case GLFW_KEY_P -> KeyCode.P;
            case GLFW_KEY_Q -> KeyCode.Q;
            case GLFW_KEY_R -> KeyCode.R;
            case GLFW_KEY_S -> KeyCode.S;
            case GLFW_KEY_T -> KeyCode.T;
            case GLFW_KEY_U -> KeyCode.U;
            case GLFW_KEY_V -> KeyCode.V;
            case GLFW_KEY_W -> KeyCode.W;
            case GLFW_KEY_X -> KeyCode.X;
            case GLFW_KEY_Y -> KeyCode.Y;
            case GLFW_KEY_Z -> KeyCode.Z;
            //Numbers (Row)
            case GLFW_KEY_0 -> KeyCode.Key0;
            case GLFW_KEY_1 -> KeyCode.Key1;
            case GLFW_KEY_2 -> KeyCode.Key2;
            case GLFW_KEY_3 -> KeyCode.Key3;
            case GLFW_KEY_4 -> KeyCode.Key4;
            case GLFW_KEY_5 -> KeyCode.Key5;
            case GLFW_KEY_6 -> KeyCode.Key6;
            case GLFW_KEY_7 -> KeyCode.Key7;
            case GLFW_KEY_8 -> KeyCode.Key8;
            case GLFW_KEY_9 -> KeyCode.Key9;
            //Numbers (Numpad)
            case GLFW_KEY_KP_0 -> KeyCode.Num0;
            case GLFW_KEY_KP_1 -> KeyCode.Num1;
            case GLFW_KEY_KP_2 -> KeyCode.Num2;
            case GLFW_KEY_KP_3 -> KeyCode.Num3;
            case GLFW_KEY_KP_4 -> KeyCode.Num4;
            case GLFW_KEY_KP_5 -> KeyCode.Num5;
            case GLFW_KEY_KP_6 -> KeyCode.Num6;
            case GLFW_KEY_KP_7 -> KeyCode.Num7;
            case GLFW_KEY_KP_8 -> KeyCode.Num8;
            case GLFW_KEY_KP_9 -> KeyCode.Num9;
            //Control
            case GLFW_KEY_BACKSPACE -> KeyCode.Backspace;
            case GLFW_KEY_LEFT_CONTROL, GLFW_KEY_RIGHT_CONTROL -> KeyCode.Crt;
            case GLFW_KEY_LEFT_SHIFT, GLFW_KEY_RIGHT_SHIFT -> KeyCode.Shift;
            case GLFW_KEY_HOME -> KeyCode.Home;
            case GLFW_KEY_CAPS_LOCK -> KeyCode.CapsLock;
            case GLFW_KEY_TAB-> KeyCode.Tab;
            case GLFW_KEY_LEFT_ALT, GLFW_KEY_RIGHT_ALT -> KeyCode.Alt;
            case GLFW_KEY_ESCAPE -> KeyCode.Esc;
            case GLFW_KEY_ENTER, GLFW_KEY_KP_ENTER -> KeyCode.Enter;
            case GLFW_KEY_DELETE -> KeyCode.Del;
            case GLFW_KEY_INSERT -> KeyCode.Ins;
            case GLFW_KEY_END -> KeyCode.End;
            //Function keys
            case GLFW_KEY_F1 -> KeyCode.F1;
            case GLFW_KEY_F2 -> KeyCode.F2;
            case GLFW_KEY_F3 -> KeyCode.F3;
            case GLFW_KEY_F4 -> KeyCode.F4;
            case GLFW_KEY_F5 -> KeyCode.F5;
            case GLFW_KEY_F6 -> KeyCode.F6;
            case GLFW_KEY_F7 -> KeyCode.F7;
            case GLFW_KEY_F8 -> KeyCode.F8;
            case GLFW_KEY_F9 -> KeyCode.F1;
            case GLFW_KEY_F10 -> KeyCode.F10;
            case GLFW_KEY_F11 -> KeyCode.F11;
            case GLFW_KEY_F12 -> KeyCode.F12;
            //Arrows
            case GLFW_KEY_UP -> KeyCode.ArrowUp;
            case GLFW_KEY_DOWN -> KeyCode.ArrowDown;
            case GLFW_KEY_RIGHT -> KeyCode.ArrowRight;
            case GLFW_KEY_LEFT -> KeyCode.ArrowLeft;
            //Misc
            case GLFW_KEY_COMMA -> KeyCode.Comma;
            case GLFW_KEY_PERIOD -> KeyCode.Dot;
            //Math
            case GLFW_KEY_KP_ADD -> KeyCode.KeyAdd;
            case GLFW_KEY_KP_DIVIDE -> KeyCode.Dived;
            case GLFW_KEY_KP_SUBTRACT -> KeyCode.Subtract;
            case GLFW_KEY_KP_MULTIPLY -> KeyCode.Multiply;
            //default
            default -> KeyCode.None;
        };

    }
}
