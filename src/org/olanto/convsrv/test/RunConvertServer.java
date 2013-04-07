/**
 * ********
 * Copyright © 2010-2012 Olanto Foundation Geneva
 *
 * This file is part of myCAT.
 *
 * myCAT is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * myCAT is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with myCAT. If not, see <http://www.gnu.org/licenses/>.
 *
 *********
 */
package org.olanto.convsrv.test;

import java.rmi.Naming;
import javax.xml.parsers.ParserConfigurationException;
import org.olanto.converter.ConfigUtil;
import org.olanto.convsrv.server.ConvertService_BASIC;

/**
 * Test du servive de conversion
 *
 *
 */
public class RunConvertServer {

    private static final int EXIT_CODE_TOO_FEW_ARGS = 255;

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: RunConvertServer config-file.xml \n");
            System.exit(EXIT_CODE_TOO_FEW_ARGS);
        } else {
            ConfigUtil.setConfigFile(args[0]);
        }
        
        ConfigUtil.loadConfigFromXml();

        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (Exception e) {
            System.out.println("RMI registry is probably running ...");
            //e.printStackTrace();
        }


        try {
            System.out.println("initialisation du convertisseur ...");

            ConvertService_BASIC idxobj = new ConvertService_BASIC();

            System.out.println("Enregistrement du serveur");

            String name = "rmi://localhost/CONVSRV";
            System.out.println("name:" + name);
            Naming.rebind(name, idxobj);
            System.out.println("Server is ready");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
