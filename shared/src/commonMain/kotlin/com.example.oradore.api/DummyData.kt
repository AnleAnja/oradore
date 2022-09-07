package com.example.oradore.api

import com.example.oradore.models.*

object DummyData {

    fun ProgramEntries(): List<ProgramEntry> {
        return listOf(
            ProgramEntry(
                "Begrüssung und Eröffnung",
                "tRpRt0bXZjAZbXGuKlwy",
                "Jedes Jahr kommen zahlreiche Teilnehmer:innen zusammen, die sich über das Thema Digitalisierung austauschen und spannende Impulse und Vorträge aus den unterschiedlichsten Themengebieten erhalten.",

                    Category.OTHER
                ,
                TimeRange(
                    1663399800000,
                    1663402500000
                ),
                Room(
                    "Jj7lxXz7Jw8CKS1vnSVf",
                    "Raum 0401/0402",
                    "FERCHAU-Hörsaal",
                    RoomLocation(
                        RoomLocation.Mensabuilding0,
                        "http://www.gm.fh-koeln.de/~dobrynin/kmm/0401.png"
                    )
                ),
                listOf(
                    Pair(
                        first = Speaker(
                            "s9OWMkGDmr2j1xHDpfWD",
                            "Torsten",
                            "Winterberg",
                            "https://lh3.googleusercontent.com/QlIbMelebRB7Gf7lm8RiITj8ZdnJp6rkdWgaKAdPGHFdnbmFXB82hmnZpIQliidV_hue7lnNcACgt4aCtkZW9z3Ia5kg6Nmi1-w",
                            "https://lh3.googleusercontent.com/b8tNeS2Zl_-agfWyUQ12PK949Zbozp4QZhmCxAKL6PoRxQXG7XfOsC7wfXLnwSFNNuHO4urtwc-VJADjcXls3jWSPJbUYcLv",
                            "",
                            "OPITZ CONSULTING / Innovation Hub",
                            "Director / Geschäftsführer",
                            "Gummersbach",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        ),
                        second = Role.SPEAKER
                    ),
                    Pair(
                        first = Speaker(
                            "4XEBwxPK45xEAKiJcZAX",
                            "Prof. Dr. Christian",
                            "Kohls",
                            "https://lh3.googleusercontent.com/P1d-hcFUDhp66KP4DbVRrMN2ALN2LsPLBOiv_cxvNHhLwuNsqILfPZLr2MgQnyw56fnGtccsQvATow4M0uOcoyqDmSHobUZe",
                            "https://lh3.googleusercontent.com/LVMaavah77lrbrDtE_TSqrZgoEFyQy-EfSuVxjlPMJCzk7V2yw7Gz5xwVNZQMxz7IQXry0MRwJjla2xwWT7saEYKfr13keFd",
                            "",
                            "TH Köln",
                            "Dekan",
                            "Köln",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        ),
                        second = Role.SPEAKER
                    ),
                    Pair(
                        first = Speaker(
                            "ASGNjBJw5G91x979hKoR",
                            "Dominik",
                            "Deimel",
                            "https://lh3.googleusercontent.com/f8Ma7YUGE_VzkNF23LHLjdHtLTM1-uJSe7iMmnmjCepk6mnyDGhrD8BUwJE3f88F-B7qBKg1QrFuSvvlVFCtHt0k5LqRvmtUpDY",
                            "https://lh3.googleusercontent.com/tJpbbka7EkmgqwwqO4Q3W4KYSZUFb7BY3v_KyEZjNA2io6apV3cUjA-tzq0BX7ykjkyZdhuK8HukPJNbPUo8ABrjeLEOFI3n",
                            "",
                            "TH Köln",
                            "Wissenschaftlicher Mitarbeiter",
                            "Gummersbach",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        ),
                        second = Role.MODERATOR
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntry(
                "Fullstack Mobile Development mit Kotlin Multiplattform",
                "e5oUQNp5ir1DW5OKNeNF",
                "Im Rahmen der Digitalisierung, die insbesondere in den letzten 20 Jahren in der Industrie und Wirtschaft an Bedeutung gewonnen hat, wächst auch die Relevanz von mobilen Anwendungen. Mobile Geräte sollen zunehmend Aufgaben in verschiedenen Lebensbereichen übernehmen oder dabei unterstützen. Um diese Anforderungen umzusetzen, gab es in der Vergangenheit diverse Ansätze des Cross- und Multiplatform-Developments, welche jedoch meist mit Problemen oder einschränkenden Kompromissen einhergehen. Eine der häufigsten Herausforderungen sind die Einarbeitung in neue und unterschiedliche Programmiersprachen sowie die Aneignung neuer Ökosysteme.\nAuf der KotlinConf 2018 wurde Kotlin Multiplatform von JetBrains vorgestellt, um Fullstack Multiplatform Apps zu schreiben. Das bedeutet, dass Backend, Frontend (iOS und Android) sowie Webanwendungen mit Kotlin entwickelt werden können. Somit kann eine gemeinsame Codebasis beispielsweise in drei Projekten wiederverwendet werden. In diesem Vortrag soll die Fullstack App Entwicklung mit Kotlin Multiplattform anhand einer mobilen Beispielanwendung gezeigt werden",
                    Category.IT_DRIVEN,
                TimeRange(
                    1663413300000,
                    1663416000000
                ),
                Room(
                    "8HpEkr7FJ6aXMhyChcuo",
                    "Raum 3103",
                    "",
                    RoomLocation(
                        RoomLocation.Mainbuilding3,
                        "http://www.gm.fh-koeln.de/~dobrynin/kmm/3103.png"
                    )
                ),
                listOf(
                    Pair(
                        first = Speaker(
                            "Hsy8aJRoXTHyjpkvE0zy",
                            "Anja",
                            "Bertels",
                            "https://lh3.googleusercontent.com/up0NNRX7lZcT1Ztkfhk9ryM_xObj0zdLZQEJDCwjridi8trtGP10SHs9WqJwRRDgcxtZCdsjBGNf8sLeIwWT7XKvgmlwHRUt6Q",
                            "https://lh3.googleusercontent.com/RqwR-9xipWVLXPrp_QiO1p7a5P60LiUmwBe1UAtijNNF-pEaaww0tdrlCsHVKZvoRbSOkr3MLSDBbTsE5Wi96cjNJS65qJhjDg",
                            "",
                            "TH Köln",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        ),
                        second = Role.SPEAKER
                    ),
                    Pair(
                        first = Speaker(
                            "tNtYblnu6AzGB6BR1zl3",
                            "Alex",
                            "Dobrynin",
                            "https://lh3.googleusercontent.com/wzWSP9EI7j1_n0VA3JXt5qzWjb3fIZCXs6RVr1iuFgGREHzVhYLiZ0KqTliDyWqfjGFNWB__s_Km4WxrFTt1ceMwgyn_AFXnpQ",
                            "https://lh3.googleusercontent.com/Chko2wf0JS10BoJ7hVhbQwL0vU-3nCdoirzP7qrcXewtXH0yEX5Fg5iMqabO6Vlis_i1elbHg04VHj8w2sK5QFpT8ZW4X6Hy",
                            "Softwaredeveloper, Autor, Dozent",
                            "TH Köln",
                            "Wissenschaftlicher Mitarbeiter",
                            "Gummersbach",
                            "www.alex-dobrynin.com",
                            "/in/alex-dobrynin-6a345b245/",
                            "profile/Alex_Dobrynin/cv",
                            "alexdobry",
                            "",
                            ""
                        ),
                        second = Role.SPEAKER
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntry(
                "Digitale Transformation - aber bitte sicher",
                "WnSbDULEBuIMNFOVo8m5",
                "Die Welt wird digital - es entstehen besondere Anforderungen an die Security</p><p>Systeme zur Angriffserkennung durchleuchten die Datenflut und erlauben Agilität. Sie ergänzen etablierte Securitymaßnahmen zur technischen und organisatorischen Sicherheit und Awareness.",
                    Category.PRIVACY,
                TimeRange(
                    1663418400000,
                    1663419600000
                ),
                Room(
                    "ka2ITleGSa6l92ZjzjNR",
                    "Raum 3106",
                    "Steinmüller-Babcock-Hörsaal",
                    RoomLocation(
                        RoomLocation.Mainbuilding3,
                        "http://www.gm.fh-koeln.de/~dobrynin/kmm/3106.png"
                    )
                ),
                listOf(
                    Pair(
                        first = Speaker(
                            "tNtYblnu6AzGB6BR1zl3",
                            "Alex",
                            "Dobrynin",
                            "https://lh3.googleusercontent.com/wzWSP9EI7j1_n0VA3JXt5qzWjb3fIZCXs6RVr1iuFgGREHzVhYLiZ0KqTliDyWqfjGFNWB__s_Km4WxrFTt1ceMwgyn_AFXnpQ",
                            "https://lh3.googleusercontent.com/Chko2wf0JS10BoJ7hVhbQwL0vU-3nCdoirzP7qrcXewtXH0yEX5Fg5iMqabO6Vlis_i1elbHg04VHj8w2sK5QFpT8ZW4X6Hy",
                            "Softwaredeveloper, Autor, Dozent",
                            "TH Köln",
                            "Wissenschaftlicher Mitarbeiter",
                            "Gummersbach",
                            "www.alex-dobrynin.com",
                            "/in/alex-dobrynin-6a345b245/",
                            "profile/Alex_Dobrynin/cv",
                            "alexdobry",
                            "",
                            ""
                        ),
                        second = Role.SPEAKER
                    )
                ),
                false,
                Format.LIGHTNING_TALK
            )
        )
    }

    fun Speakers(): List<Speaker> {
        return listOf(
            Speaker(
                "4XEBwxPK45xEAKiJcZAX",
                "Prof. Dr. Christian",
                "Kohls",
                "https://lh3.googleusercontent.com/P1d-hcFUDhp66KP4DbVRrMN2ALN2LsPLBOiv_cxvNHhLwuNsqILfPZLr2MgQnyw56fnGtccsQvATow4M0uOcoyqDmSHobUZe",
                "https://lh3.googleusercontent.com/LVMaavah77lrbrDtE_TSqrZgoEFyQy-EfSuVxjlPMJCzk7V2yw7Gz5xwVNZQMxz7IQXry0MRwJjla2xwWT7saEYKfr13keFd",
                "",
                "TH Köln",
                "Dekan",
                "Köln",
                "",
                "",
                "",
                "",
                "",
                ""
            ),
            Speaker(
                "s9OWMkGDmr2j1xHDpfWD",
                "Torsten",
                "Winterberg",
                "https://lh3.googleusercontent.com/QlIbMelebRB7Gf7lm8RiITj8ZdnJp6rkdWgaKAdPGHFdnbmFXB82hmnZpIQliidV_hue7lnNcACgt4aCtkZW9z3Ia5kg6Nmi1-w",
                "https://lh3.googleusercontent.com/b8tNeS2Zl_-agfWyUQ12PK949Zbozp4QZhmCxAKL6PoRxQXG7XfOsC7wfXLnwSFNNuHO4urtwc-VJADjcXls3jWSPJbUYcLv",
                "",
                "OPITZ CONSULTING / Innovation Hub",
                "Director / Geschäftsführer",
                "Gummersbach",
                "",
                "",
                "",
                "",
                "",
                ""
            ),
            Speaker(
                "Hsy8aJRoXTHyjpkvE0zy",
                "Anja",
                "Bertels",
                "https://lh3.googleusercontent.com/up0NNRX7lZcT1Ztkfhk9ryM_xObj0zdLZQEJDCwjridi8trtGP10SHs9WqJwRRDgcxtZCdsjBGNf8sLeIwWT7XKvgmlwHRUt6Q",
                "https://lh3.googleusercontent.com/RqwR-9xipWVLXPrp_QiO1p7a5P60LiUmwBe1UAtijNNF-pEaaww0tdrlCsHVKZvoRbSOkr3MLSDBbTsE5Wi96cjNJS65qJhjDg",
                "",
                "TH Köln",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            ),
            Speaker(
                "tNtYblnu6AzGB6BR1zl3",
                "Alex",
                "Dobrynin",
                "https://lh3.googleusercontent.com/wzWSP9EI7j1_n0VA3JXt5qzWjb3fIZCXs6RVr1iuFgGREHzVhYLiZ0KqTliDyWqfjGFNWB__s_Km4WxrFTt1ceMwgyn_AFXnpQ",
                "https://lh3.googleusercontent.com/Chko2wf0JS10BoJ7hVhbQwL0vU-3nCdoirzP7qrcXewtXH0yEX5Fg5iMqabO6Vlis_i1elbHg04VHj8w2sK5QFpT8ZW4X6Hy",
                "Softwaredeveloper, Autor, Dozent",
                "TH Köln",
                "Wissenschaftlicher Mitarbeiter",
                "Gummersbach",
                "www.alex-dobrynin.com",
                "/in/alex-dobrynin-6a345b245/",
                "profile/Alex_Dobrynin/cv",
                "alexdobry",
                "",
                ""
            ),
            Speaker(
                "ASGNjBJw5G91x979hKoR",
                "Dominik",
                "Deimel",
                "https://lh3.googleusercontent.com/f8Ma7YUGE_VzkNF23LHLjdHtLTM1-uJSe7iMmnmjCepk6mnyDGhrD8BUwJE3f88F-B7qBKg1QrFuSvvlVFCtHt0k5LqRvmtUpDY",
                "https://lh3.googleusercontent.com/tJpbbka7EkmgqwwqO4Q3W4KYSZUFb7BY3v_KyEZjNA2io6apV3cUjA-tzq0BX7ykjkyZdhuK8HukPJNbPUo8ABrjeLEOFI3n",
                "",
                "TH Köln",
                "Wissenschaftlicher Mitarbeiter",
                "Gummersbach",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
    }

    fun Rooms(): List<Room> {
        return listOf(
            Room(
                "ka2ITleGSa6l92ZjzjNR",
                "Raum 3106",
                "Steinmüller-Babcock-Hörsaal",
                RoomLocation(
                    RoomLocation.Mainbuilding3,
                    "http://www.gm.fh-koeln.de/~dobrynin/kmm/3106.png"
                )
            ),
            Room(
                "8HpEkr7FJ6aXMhyChcuo",
                "Raum 3103",
                "",
                RoomLocation(
                    RoomLocation.Mainbuilding3,
                    "http://www.gm.fh-koeln.de/~dobrynin/kmm/3103.png"
                )
            ),
            Room(
                "q5ziDJLrl5LRgDTKqx0a",
                "Raum 3102",
                "PFERD-Hörsaal",
                RoomLocation(
                    RoomLocation.Mainbuilding3,
                    "http://www.gm.fh-koeln.de/~dobrynin/kmm/3102.png"
                )
            ),
            Room(
                "Jj7lxXz7Jw8CKS1vnSVf",
                "Raum 0401/0402",
                "FERCHAU-Hörsaal",
                RoomLocation(
                    RoomLocation.Mensabuilding0,
                    "http://www.gm.fh-koeln.de/~dobrynin/kmm/0401.png"
                )
            ),
            Room(
                "Z47wC8xGHqBvyMa6DDtu",
                "Serious Playground",
                "Halle 51",
                null
            )
        )
    }
}