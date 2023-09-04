# Define the header
$headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"
$headers.Add("Content-Type", "application/json")

# Define the body
$body = @{
    userId = "jane"
    password = "pass1234"
} | ConvertTo-Json

# Make the POST request
$response = Invoke-RestMethod -Uri "http://localhost:9000/songsMS/rest/auth" -Method POST -Headers $headers -Body $body

# Output the response
$response
