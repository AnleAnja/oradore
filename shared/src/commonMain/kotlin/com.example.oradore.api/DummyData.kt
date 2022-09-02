package com.example.oradore.api

import com.example.oradore.models.*

object DummyData {
    fun ProgramEntries(): List<ProgramEntryPreview> {
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
}