<!-- BEGIN -->
@import exercise.dto.users.UserPage
@param UserPage page

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
              crossorigin="anonymous">
        <style>
            table {
              font-family: arial, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }

            td, th {
              border: 1px solid #dddddd;
              text-align: left;
              padding: 8px;
            }

            tr:nth-child(even) {
              background-color: #dddddd;
            }
        </style>
        <title>User</title>
    </head>

    <body>
        <div class="mx-auto p-4 py-md-5">
            <main>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                    </tr>
                    <tr>
                        <td>${page.getUser().getId()}</td>
                        <td>${page.getUser().getFirstName()}</td>
                        <td>${page.getUser().getLastName()}</td>
                        <td>${page.getUser().getEmail()}</td>
                    </tr>
                </table>
            </main>
        </div>
    </body>
</html>
<!-- END -->
