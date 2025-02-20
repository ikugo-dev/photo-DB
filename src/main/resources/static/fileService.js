export const localHost = "http://localhost:8080";

export async function uploadFile() {
    const fileUpload = document.getElementById("file-upload");
    let formData = new FormData();
    formData.append("payload", fileUpload.files[0]);
    const response = await fetch(`${localHost}/photos`, {
        method: "POST",
        body: formData
    });
    return response.ok;
}
export async function downloadFile(id) {
    const response = await fetch(`${localHost}/download/${id}`, {
        method: "GET",
    });
    if (!response.ok) throw new Error("Download failed");

    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = `image-${id}.jpg`; // Adjust file extension if needed
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
}
export async function deleteFile(id) {
    const confirmDelete = confirm("Are you sure you want to delete this image?");
    if (!confirmDelete) return;

    const response = await fetch(`${localHost}/photos/${id}`, {
        method: "DELETE",
    });
    return response.ok;
}