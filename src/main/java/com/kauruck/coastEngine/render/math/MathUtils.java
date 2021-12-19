package com.kauruck.coastEngine.render.math;

import com.kauruck.coastEngine.centum.componetns.Transform;
import com.kauruck.coastEngine.core.math.Vector3;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MathUtils {
        public static Matrix4f createTransformationMatrix(Transform transform){
                Matrix4f out = new Matrix4f();
                out.identity();
                out = out.translate((float) transform.getPosition().getX(), (float) transform.getPosition().getY(), (float) transform.getPosition().getZ());
                out = out.rotate((float) Math.toRadians(transform.getRotation().getX()), new Vector3f(1,0,0), out);
                out = out.rotate((float) Math.toRadians(transform.getRotation().getY()), new Vector3f(0,1,0), out);
                out = out.rotate((float) Math.toRadians(transform.getRotation().getZ()), new Vector3f(0,0,1), out);
                out = out.scale(transform.getScale());
                return out;
        }

        public static final Matrix4f ZERO_TRANSFORM = new Matrix4f().identity();

}
