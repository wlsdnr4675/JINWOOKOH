import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import ListItemText from '@material-ui/core/ListItemText';
import ListItem from '@material-ui/core/ListItem';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { readResume } from '../reducer/resume.reduce';

const ResumeRead = ({resumeId, open , handleClose}) => {



    const items = useSelector(state => state.resumes.current);
    const files = items.resumeFiles;
    const [resumeItem,setResumeItem] = useState({});
    const [resumeFile,setResumeFile] = useState([]);
    

    console.log("Read ResumeId",resumeId)
    console.log("2",items)
    console.log("3",handleClose)

    useEffect(()=>{
        setResumeItem(items)
        setResumeFile(files)
    },[items]) 

    const useStyles = makeStyles((theme) => ({
        appBar: {
          position: 'relative',
          backgroundColor:"#561571"
        },
        title: {
          marginLeft: theme.spacing(2),
          flex: 1,
          fontSize: 34,
          color:  "white"
        },
        closeSize: {
            fontSize: 20,
            margin: 0
            
        },
        dialogSize : {
            margin: 'auto auto',
            width: '70%', 
            maxWidth: 'none', height :"90%", textAlign : "center",
            verticalAlign: "middle"
            
        }
      }));
      
      const Transition = React.forwardRef(function Transition(props, ref) {

        return <Slide direction="up" ref={ref} {...props} />;
      });
      
    
    return (<>
        <Dialog fullScreen open={open} onClose={handleClose} TransitionComponent={Transition} className={useStyles().dialogSize}
        >
          <AppBar className={useStyles().appBar}>
            <Toolbar >
              <IconButton edge="start"  onClick={handleClose} aria-label="close">
                <CloseIcon className={useStyles().closeSize}/>
              </IconButton>
              <Typography variant="h6" className={useStyles().title}>
                {resumeItem.name} 의 포트폴리오
              </Typography>
              <Button autoFocus color="inherit" onClick={handleClose}>
                수정하기
              </Button>
            </Toolbar>
          </AppBar>
          <List>
            <ListItem button>
              <ListItemText primary="Phone ringtone" secondary="Titania" />
            </ListItem>
            <Divider />
            <ListItem button>
              <ListItemText primary="Default notification ringtone" secondary="Tethys" />
            </ListItem>
          </List>
        </Dialog>
    </>)
}

export default ResumeRead;

// import { makeStyles } from '@material-ui/core/styles';

// import GridListTile from '@material-ui/core/GridListTile';
// import GridListTileBar from '@material-ui/core/GridListTileBar';
// import IconButton from '@material-ui/core/IconButton';
// import StarBorderIcon from '@material-ui/icons/StarBorder';
// import tileData from './tileData';

// const useStyles = makeStyles((theme) => ({
//   root: {
//     display: 'flex',
//     flexWrap: 'wrap',
//     justifyContent: 'space-around',
//     overflow: 'hidden',
//     backgroundColor: theme.palette.background.paper,
//   },
//   gridList: {
//     width: 500,
//     height: 450,
//     // Promote the list into his own layer on Chrome. This cost memory but helps keeping high FPS.
//     transform: 'translateZ(0)',
//   },
//   titleBar: {
//     background:
//       'linear-gradient(to bottom, rgba(0,0,0,0.7) 0%, ' +
//       'rgba(0,0,0,0.3) 70%, rgba(0,0,0,0) 100%)',
//   },
//   icon: {
//     color: 'white',
//   },
// }));







/**
 * The example data is structured as follows:
 *
 * import image from 'path/to/image.jpg';
 * [etc...]
 *
 * const tileData = [
 *   {
 *     img: image,
 *     title: 'Image',
 *     author: 'author',
 *     featured: true,
 *   },
 *   {
 *     [etc...]
 *   },
 * ];
 */
// export default function AdvancedGridList() {
//   const classes = useStyles();

//   return (
//     <div className={classes.root}>
//       <GridList cellHeight={200} spacing={1} className={classes.gridList}>
//         {tileData.map((tile) => (
//           <GridListTile key={tile.img} cols={tile.featured ? 2 : 1} rows={tile.featured ? 2 : 1}>
//             <img src={tile.img} alt={tile.title} />
//             <GridListTileBar
//               title={tile.title}
//               titlePosition="top"
//               actionIcon={
//                 <IconButton aria-label={`star ${tile.title}`} className={classes.icon}>
//                   <StarBorderIcon />
//                 </IconButton>
//               }
//               actionPosition="left"
//               className={classes.titleBar}
//             />
//           </GridListTile>
//         ))}
//       </GridList>
//     </div>
//   );
// }
