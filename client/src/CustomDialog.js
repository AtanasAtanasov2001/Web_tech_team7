import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

export default function CustomDialog({ open, children, title, contentText, handleContinue, handlePlayAgain }) {
    return (
        <Dialog open={open}>
            <DialogTitle>{title}</DialogTitle>
            <DialogContent>
                <DialogContentText>{contentText}</DialogContentText>
                {children}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleContinue}>Continue</Button>
                <Button onClick={handlePlayAgain}>Play Again</Button>
            </DialogActions>
        </Dialog>
    );
}
