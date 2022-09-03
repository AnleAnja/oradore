package com.example.oradore.api

import com.example.oradore.models.*

object DummyData {
    fun ProgramEntriesPreview(): List<ProgramEntryPreview> {
        return listOf(
            ProgramEntryPreview(
                "Begrüssung und Eröffnung",
                "tRpRt0bXZjAZbXGuKlwy",
                Category.OTHER,
                TimeRange(
                    1663399800000,
                    1663402500000
                ),
                Room(
                    "Jj7lxXz7Jw8CKS1vnSVf",
                    "Raum 0401/0402 | FERCHAU-Hörsaal"
                ),
                listOf(
                    SpeakerPreview(
                        "s9OWMkGDmr2j1xHDpfWD",
                        "Torsten",
                        "Winterberg",
                        "https://lh3.googleusercontent.com/b8tNeS2Zl_-agfWyUQ12PK949Zbozp4QZhmCxAKL6PoRxQXG7XfOsC7wfXLnwSFNNuHO4urtwc-VJADjcXls3jWSPJbUYcLv",
                        "OPITZ CONSULTING / Innovation Hub",
                        "Director / Geschäftsführer"
                    ),
                    SpeakerPreview(
                        "4XEBwxPK45xEAKiJcZAX",
                        "Prof. Dr. Christian",
                        "Kohls",
                        "https://lh3.googleusercontent.com/LVMaavah77lrbrDtE_TSqrZgoEFyQy-EfSuVxjlPMJCzk7V2yw7Gz5xwVNZQMxz7IQXry0MRwJjla2xwWT7saEYKfr13keFd",
                        "TH Köln",
                        "Dekan"
                    ),
                    SpeakerPreview(
                        "hEHC95pLG3DaOOUuuu2p",
                        "Frank",
                        "Hoppe",
                        "https://lh3.googleusercontent.com/fyrJivMpSP7BVq0qV-Usv0fbx0rqOW5Rrg2aPJ3HTob6C0PWuASX33rUru7mPYrXyw0WXfcZlwZaPdKxDKeYQuAQ5nFtjTa4NA",
                        "OPITZ CONSULTING",
                        "Innovation Coach"
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntryPreview(
                "Fullstack Mobile Development mit Kotlin Multiplattform",
                "e5oUQNp5ir1DW5OKNeNF",
                Category.IT_DRIVEN,
                TimeRange(
                    1663413300000,
                    1663416000000
                ),
                Room(
                    "8HpEkr7FJ6aXMhyChcuo",
                    "Raum 3103"
                ),
                listOf(
                    SpeakerPreview(
                        "Hsy8aJRoXTHyjpkvE0zy",
                        "Anja",
                        "Bertels",
                        "https://lh3.googleusercontent.com/RqwR-9xipWVLXPrp_QiO1p7a5P60LiUmwBe1UAtijNNF-pEaaww0tdrlCsHVKZvoRbSOkr3MLSDBbTsE5Wi96cjNJS65qJhjDg",
                        "TH Köln",
                        ""
                    ),
                    SpeakerPreview(
                        "tNtYblnu6AzGB6BR1zl3",
                        "Alex",
                        "Dobrynin",
                        "https://lh3.googleusercontent.com/Chko2wf0JS10BoJ7hVhbQwL0vU-3nCdoirzP7qrcXewtXH0yEX5Fg5iMqabO6Vlis_i1elbHg04VHj8w2sK5QFpT8ZW4X6Hy",
                        "TH Köln",
                        "Wissenschaftlicher Mitarbeiter"
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntryPreview(
                "Digitale Transformation - aber bitte sicher",
                "WnSbDULEBuIMNFOVo8m5",
                Category.PRIVACY,
                TimeRange(
                    1663418400000,
                    1663419600000
                ),
                Room(
                    "sF1w3rpkux6tnJbLDxJd",
                    "Raum 3112"
                ),
                listOf(
                    SpeakerPreview(
                        "CU6JjNMKuG0fo0jLSdRU",
                        "Fred",
                        "Schmidt",
                        "https://lh3.googleusercontent.com/xeWjWSA-VY84igOjNKdf6ficYkRA90Bv7KNa_627TbtbilYzK9uSEqXqjY6XzgpQ8pT_1lC2iJ5JcW8CEDtsKBFlzyQsHMJsJg",
                        "TÜV TRUST IT GmbH",
                        ""
                    )
                ),
                false,
                Format.LIGHTNING_TALK
            )
        )
    }
    fun ProgramEntries(): List<ProgramEntry> {
        return listOf(
            ProgramEntry(
                "Begrüssung und Eröffnung",
                "tRpRt0bXZjAZbXGuKlwy",
                "Die Überwachung der Luftreinheit in geschlossenen Räumen sowie der damit verbundenen Kontamination durch Viren und Bakterien ist außerhalb von Räumlichkeiten wie den Krankenhäusern, der Pharmazie oder der Lebensmittelindustrie zu einem wichtigen Thema geworden. Die momentan auf dem Markt erwerbsfähigen Überwachungssysteme zur Luftreinheit in geschlossenen Räumen, auch Monitoring-Systeme genannt, übersteigen aufgrund von hohen Anschaffungs- und Betriebskosten die finanziellen Möglichkeiten von kleinen und mittelständischen Unternehmen. Diese agieren zudem nicht intelligent und automatisiert, sodass keine hard- und softwaregestütze Datenerfassung und -auswertung zur Luftqualitätssteuerung in Reinräumen unterstützt wird. Im Rahmen des zentralen Innovationsprogramm Mittelstand (ZIM) wird in einem Forschungs- und Entwicklungsaktivitäten- Kooperationsprojekt zwischen der TH Köln und der WHO Reinraumtechnik ein innovatives Reinheitsmonitoring-System zur aktiven, intelligenten Luftqualitätssteuerung und Energieeinsparung entwickelt. Auf Grundlage der anerkannten Regelwerke der Reinraumtechnik sowie empirischer Studien wird neben der Mess- und Netzwerktechnik, die geeignete Sensorik zur Überwachung und Luftqualitätssteuerung für das Monitoring-System realisiert. Aus verschiedenen Anforderungsprofilen zur Entwicklung der Sensorik und eines geeigneten Algorithmus, welches in einen Prototyp implementiert wird, ermöglicht durch eine Langzeit-Datenaufnahme sowie Luftqualitätsauswertung ein KI-gestütztes Monitoring-System. Mit Hilfe einer mobilen Anbindung einer Steuersoftware kann die Luftreinheit in geschlossenen Räumen in Echtzeit mitverfolgt und zudem optional manuell gesteuert werden. Während der Konzipierungs- und Entwicklungsphase werden Optimierungen der Luftqualitätsparameter zur Reinheit, Sicherheit und Gesundheit vorgenommen, um weitere Potentialitäten im Bereich der Energieeinsparung und nachhaltigen Technologieentwicklung zu ermöglichen.",
                listOf(
                    Category.OTHER
                ),
                TimeRange(
                    1663399800000,
                    1663402500000
                ),

                "Jj7lxXz7Jw8CKS1vnSVf",
                listOf(
                    SpeakerRef(
                        "s9OWMkGDmr2j1xHDpfWD",
                        Role.SPEAKER
                    ),
                    SpeakerRef(
                        "4XEBwxPK45xEAKiJcZAX",
                        Role.SPEAKER
                    ),
                    SpeakerRef(
                        "hEHC95pLG3DaOOUuuu2p",
                        Role.SPEAKER
                    ),
                    SpeakerRef(
                        "hEHC95pLG3DaOOUuuu2p",
                        Role.MODERATOR
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntry(
                "Fullstack Mobile Development mit Kotlin Multiplattform",
                "e5oUQNp5ir1DW5OKNeNF",
                "Im Rahmen der Digitalisierung, die insbesondere in den letzten 20 Jahren in der Industrie und Wirtschaft an Bedeutung gewonnen hat, wächst auch die Relevanz von mobilen Anwendungen. Mobile Geräte sollen zunehmend Aufgaben in verschiedenen Lebensbereichen übernehmen oder dabei unterstützen. Um diese Anforderungen umzusetzen, gab es in der Vergangenheit diverse Ansätze des Cross- und Multiplatform-Developments, welche jedoch meist mit Problemen oder einschränkenden Kompromissen einhergehen. Eine der häufigsten Herausforderungen sind die Einarbeitung in neue und unterschiedliche Programmiersprachen sowie die Aneignung neuer Ökosysteme.\nAuf der KotlinConf 2018 wurde Kotlin Multiplatform von JetBrains vorgestellt, um Fullstack Multiplatform Apps zu schreiben. Das bedeutet, dass Backend, Frontend (iOS und Android) sowie Webanwendungen mit Kotlin entwickelt werden können. Somit kann eine gemeinsame Codebasis beispielsweise in drei Projekten wiederverwendet werden. In diesem Vortrag soll die Fullstack App Entwicklung mit Kotlin Multiplattform anhand einer mobilen Beispielanwendung gezeigt werden",
                listOf(
                    Category.IT_DRIVEN
                ),
                TimeRange(
                    1663413300000,
                    1663416000000
                ),
                "8HpEkr7FJ6aXMhyChcuo",
                listOf(
                    SpeakerRef(
                        "Hsy8aJRoXTHyjpkvE0zy",
                        Role.SPEAKER
                    ),
                    SpeakerRef(
                        "tNtYblnu6AzGB6BR1zl3",
                        Role.SPEAKER
                    )
                ),
                false,
                Format.TALK
            ),
            ProgramEntry(
                "Digitale Transformation - aber bitte sicher",
                "WnSbDULEBuIMNFOVo8m5",
                "Die Welt wird digital - es entstehen besondere Anforderungen an die Security</p><p>Systeme zur Angriffserkennung durchleuchten die Datenflut und erlauben Agilität. Sie ergänzen etablierte Securitymaßnahmen zur technischen und organisatorischen Sicherheit und Awareness.",
                listOf(
                    Category.PRIVACY
                ),
                TimeRange(
                    1663418400000,
                    1663419600000
                ),
                    "sF1w3rpkux6tnJbLDxJd",

                listOf(
                    SpeakerRef(
                        "CU6JjNMKuG0fo0jLSdRU",
                        Role.SPEAKER
                    )
                ),
                false,
                Format.LIGHTNING_TALK
            )
        )
    }

}