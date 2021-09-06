package com.velmurugan.androidanimationexample

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.util.*

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response
        val method = chain.request().method.toUpperCase(Locale.ROOT)
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.contains("movie") -> getLoginResponse

            else -> ""
        }

        response = Response.Builder().code(200).protocol(Protocol.HTTP_2).message(responseString)
            .request(chain.request()).body(
                responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
            ).addHeader("content-type", "application/json").build()

        return response
    }





    private val getLoginResponse = """
[{
        "category": "Latest",
        "imageUrl": "https://howtodoandroid.com/images/coco.jpg",
        "name": "Coco",
        "desc": "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"
    },
    {
        "category": "Latest",
        "imageUrl": "https://howtodoandroid.com/images/terminator_2.jpg",
        "name": "Terminator 2: Judgment Day 3D",
        "desc": "Similar to Cameron's Titanic 3D, Lightstorm Entertainment oversaw the work on the 3D version of Terminator 2, which took nearly a year to finish."
    },
    {
        "category": "Latest",
        "imageUrl": "https://howtodoandroid.com/images/dunkirk.jpg",
        "name": "Dunkirk",
        "desc": "Dunkirk is a 2017 war film written, directed, and co-produced by Christopher Nolan that depicts the Dunkirk evacuation of World War II. "
    },
    {
        "category": "Favorites",
        "imageUrl": "https://howtodoandroid.com/images/lion.png",
        "name": "Lion",
        "desc": "Lion is a 2016 Australian biographical drama film directed by Garth Davis (in his feature debut) and written by Luke Davies, based on the non-fiction book A Long Way Home by Saroo Brierley."
    },
    {
        "category": "High Rated",
        "imageUrl": "https://howtodoandroid.com/images/star_war.jpg",
        "name": "Star Wars: The Last Jedi",
        "desc": "Star Wars: The Last Jedi (also known as Star Wars: Episode VIII – The Last Jedi) is a 2017 American epic space opera film written and directed by Rian Johnson."
    },
    {
        "category": "High Rated",
        "imageUrl": "https://howtodoandroid.com/images/thor_ragnarok.jpg",
        "name": "Thor: Ragnarok",
        "desc": "Thor: Ragnarok is a 2017 American superhero film based on the Marvel Comics character Thor, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures."
    },
    {
        "category": "High Rated",
        "imageUrl": "https://howtodoandroid.com/images/blade_runner_2049.jpg",
        "name": "Blade Runner 2049",
        "desc": "Blade Runner 2049 is a 2017 American science fiction film directed by Denis Villeneuve and written by Hampton Fancher and Michael Green. "
    },
    {
        "category": "High Rated",
        "imageUrl": "https://howtodoandroid.com/images/borg_mcenroe.jpg",
        "name": "Borg McEnroe",
        "desc": "Borg McEnroe also known as Borg vs McEnroe, is a 2017 internationally co-produced multi-language biographical sports drama film focusing on the famous rivalry between famous tennis players "
    },
    {
        "category": "High Rated",
        "imageUrl": "https://howtodoandroid.com/images/wonder.jpg",
        "name": "Wonder",
        "desc": "Wonder is a 2017 American drama film directed by Stephen Chbosky and written by Jack Thorne , Steve Conrad and Stephen Chbosky based on the 2012 novel of the same name by R.J. Palacio."
    }
]
"""
    private val getUserInfo = """
        {"userId":"HTCI007","primaryRole":"Bumper_Admin","additionalRoles":null}
    """.trimIndent()


    private val getAllModelResponse = """
        [
          {
            "bodyTypeCD": "M1",
            "bodyTypeDesc": "mk"
          },
          {
            "bodyTypeCD": "mk",
            "bodyTypeDesc": "te"
          }
        ]
     """.trimIndent()
    private val getColorByModelResponse = """
        [
          {
            "colorCD": "C2",
            "colorDesc": "CT",
            "bodyTypeCD": "M1",
            "htmlcolorCD": "#121212"
          },
          {
            "colorCD": "Y",
            "colorDesc": "string",
            "bodyTypeCD": "M1",
            "htmlcolorCD": "string"
          }
        ]
    """.trimIndent()
    private val getBumperByModelResponse = """
        [
          {
            "bumperCD": "R1",
            "bumperDesc": "Test 123",
            "bodyTypeCD": "M1"
          },
          {
            "bumperCD": "T1",
            "bumperDesc": "B2",
            "bodyTypeCD": "M1"
          }
        ]
    """.trimIndent()
    private val submittedOrdersResponse = """
       [
          {
   "orderId":10001,
   "modelName":"Ascent",
   "modelCd":"M1",
   "colorName":"Light Red",
   "colorCd":"C1",
   "bumperName":"Front",
   "bumperCd":"R1",
   "submittedDate":"12/02/2021 12:34 PM",
   "submittedBy":"HTCI007",
   "bumperCount":2,
   "acknowledgedBy":"HTCI004",
   "acknowledgedDate":"12/02/2021 13:34 PM",
   "completedBy":"HTCI003",
   "completedDate":"12/02/2021 13:45 PM",
   "closedBy":"HTCI002",
   "closedDate":"12/02/2021 14:23 PM"
},
          {
             "bumperCD":"C2",
             "colorCD":"R3",
             "isSelected":false,
             "bodyTypeCD":"M2",
             "nmberOfBumpers":1,
             "orderProcessedBy":"admin",
             "orderProcessedInd":true,
             "orderStatus":"SI",
             "orderSubmittedSectionId":"07"
          }
       ]
    """.trimIndent()
    private val inProgressOrdersResponse = """
       [
          {
             "bumperCD":"C3",
             "colorCD":"R2",
             "isSelected":false,
             "bodyTypeCD":"M3",
             "nmberOfBumpers":1,
             "orderProcessedBy":"admin",
             "orderProcessedInd":true,
             "orderStatus":"SI",
             "orderSubmittedSectionId":"07"
          },
          {
             "bumperCD":"C4",
             "colorCD":"R4",
             "isSelected":false,
             "bodyTypeCD":"M4",
             "nmberOfBumpers":1,
             "orderProcessedBy":"admin",
             "orderProcessedInd":true,
             "orderStatus":"SI",
             "orderSubmittedSectionId":"07"
          }
       ]
    """.trimIndent()
    private val completedOrdersResponse = """
       [
          {
             "bumperCD":"C4",
             "colorCD":"R5",
             "isSelected":false,
             "bodyTypeCD":"M5",
             "nmberOfBumpers":1,
             "orderProcessedBy":"admin",
             "orderProcessedInd":true,
             "orderStatus":"SI",
             "orderSubmittedSectionId":"07"
          },
          {
             "bumperCD":"C5",
             "colorCD":"R6",
             "isSelected":false,
             "bodyTypeCD":"M6",
             "nmberOfBumpers":1,
             "orderProcessedBy":"admin",
             "orderProcessedInd":true,
             "orderStatus":"SI",
             "orderSubmittedSectionId":"07"
          }
       ]
    """.trimIndent()
}