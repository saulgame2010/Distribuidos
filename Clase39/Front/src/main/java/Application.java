/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import networking.WebServer;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        int currentServerPort = 9000;
        if (args.length == 1) {
            currentServerPort = Integer.parseInt(args[0]);
        }

        String []ip = new String[3];
        ip[0] = "http://"+args[1]+"/searchphrase";
        ip[1] = "http://"+args[2]+"/searchphrase";
        ip[2] = "http://"+args[3]+"/searchphrase";

        String []librosip1 = {"Adler,_Elizabeth__1991_._La_esmeralda_de_los_Ivanoff_[10057].txt", "Adler_Olsen,_Jussi__1997_._La_casa_del_alfabeto_[7745].txt", "Aguilera,_Juan_Miguel__1998_._La_locura_de_Dios_[5644].txt", "Alameddine,_Rabih__2008_._El_contador_de_historias_[5735].txt", "Albom,_Mitch__2002_._Martes_con_mi_viejo_profesor_[382].txt", "Alcott,_Louisa_May__1868_._Mujercitas_[11086].txt", "Alcott,_Louisa_May__1871_._Hombrecitos_[15392].txt", "Alders,_Hanny__1987_._El_tesoro_de_los_templarios_[13014].txt", "Alexander,_Caroline__1998_._Atrapados_en_el_hielo_[15727].txt", "Allende,_Isabel__1982_._La_casa_de_los_espiritus_[563].txt", "Allende,_Isabel__1984_._De_amor_y_de_sombra_[6283].txt", "Alten,_Steve__2001_.__Trilogia_maya_01__El_testamento_maya_[8901].txt", "Alten,_Steve__2008_._Al_borde_del_infierno_[12141].txt", "Amis,_Martin__1990_._Los_monstruos_de_Einstein_[8080].txt", "Anderson,_Sienna__2008_._No_me_olvides_[15047].txt"};
        String []librosip2 = {"Anonimo__2004_._Robin_Hood_[11853].txt", "Archer,_Jeffrey__1979_._Kane_y_Abel_[1965].txt", "Asimov,_Isaac__1950_._Yo,_robot_[10874].txt", "Asimov,_Isaac__1967_._Guia_de_la_Biblia__Antiguo_Testamento__[6134].txt", "Asimov,_Isaac__1985_._El_monstruo_subatomico_[167].txt", "Bach,_Richard__1970_._Juan_Salvador_Gaviota_[15399].txt", "Baum,_Lyman_Frank__1900_._El_Mago_de_Oz_[15715].txt", "Beevor,_Antony__1998_._Stalingrado_[10491].txt", "Benitez,_J._J.__1984_.__Caballo_de_Troya_01__Jerusalen_[4826].txt", "Dickens,_Charles__1843_._Cuento_de_Navidad_[3285].txt", "Dostoievski,_Fiodor__1865_._Crimen_y_castigo_[13400].txt", "Ende,_Michael__1973_._Momo_[1894].txt", "Esquivel,_Laura__1989_._Como_agua_para_chocolate_[7750].txt", "Flaubert,_Gustave__1857_._Madame_Bovary_[3067].txt", "Fromm,_Erich__1947_._El_miedo_a_la_libertad_[11619].txt", "Gaarder,_Jostein__1991_._El_mundo_de_Sofia_[6571].txt"};
        String []librosip3 = {"Gaiman,_Neil__2002_._Coraline_[1976].txt", "Garcia_Marquez,_Gabriel__1967_._Cien_anos_de_soledad_[8376].txt", "Garcia_Marquez,_Gabriel__1985_._El_amor_en_los_tiempos_del_colera_[874].txt", "Garcia_Marquez,_Gabriel__1989_._El_general_en_su_laberinto_[875].txt", "Golding,_William__1954_._El_senor_de_las_moscas_[6260].txt", "Goleman,_Daniel__1995_._Inteligencia_emocional_[4998].txt", "Gorki,_Maximo__1907_._La_madre_[1592].txt", "Harris,_Thomas__1988_._El_silencio_de_los_inocentes_[11274].txt", "Hawking,_Stephen__1988_._Historia_del_tiempo_[8536].txt", "Hemingway,_Ernest__1952_._El_viejo_y_el_mar_[1519].txt", "Hesse,_Herman__1919_._Demian_[2612].txt", "Hitler,_Adolf__1935_._Mi_lucha_[11690].txt", "Hobbes,_Thomas__1651_._Leviatan_[2938].txt", "Huxley,_Aldous__1932_._Un_mundo_feliz_[293].txt", "Anonimo__1554_._Lazarillo_de_Tormes_[11043].txt"};

        WebServer webServer = new WebServer(currentServerPort, ip, librosip1, librosip2, librosip3);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto: " + currentServerPort);
    }
}
