package games.mgd.archery.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Michael on 14/02/2016.
 */
public class TextResourceLoader {
    public static String loadFromResource(Context context, int resourceId){
        StringBuilder builder = new StringBuilder();

        try{
            InputStream is = context.getResources().openRawResource(resourceId);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is)
            );

            String nextLine;

            while ((nextLine = br.readLine()) != null) {
                builder.append(nextLine);
                builder.append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not open resource: " + resourceId, e);
        } catch (Resources.NotFoundException nfe) {
            throw new RuntimeException("Resource not found: "
                    + resourceId, nfe);
        }

        return builder.toString();
    }
}
