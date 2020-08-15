;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GAME.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
;;;;				Greg Tomko-Pavia
;;;;
;;;;	This module contains the classes which implement much of the behavior
;;;;	of an adventure game.
;;;;
;;;;	Classes:
;;;;		Sounds
;;;;		Game
;;;;		Region
;;;;		Room
;;;;		StatusLine
;;;;


(script#	GAME)
(include game.sh) (include "64994.shm")
(use Main)
(use Styler)
(use Plane)
(use String)
(use Print)
(use Polygon)
(use Cursor)
(use Sound)
(use Save)
(use Motion)
(use User)
(use System)


;;;(extern
;;;	GetDirectory	SAVE	0
;;;)


; Global lists

(instance theCast of Cast
	(properties
		name	"cast"
	)
)

(instance theFeatures of EventHandler
	(properties
		name "features"
	)
)

(instance demons of EventHandler
	(properties
		name "theDoits"
	)
)

(instance thePrints of EventHandler
	(properties
		name "prints"
	)
)

(instance thePlanes of Set
	(properties
		name	"planes"
	)
	(method (eachElementDo action &tmp node aCastList obj)
		;
		; Overridden to pass the action on to all the planes' casts
		(for	((= node (List LFirstNode elements)))
				node
				((= node nextNode))
			(= nextNode (List LNextNode node))
			(= aCastList ((List LNodeValue node) casts?))

			(for	((= node (List LFirstNode (aCastList elements?))))
					node
					((= node (aCastList nextNode?)))
				(aCastList nextNode: (List LNextNode node))
				(= obj (List LNodeValue node))
				(obj eachElementDo: action &rest)
			)
		)
	)
)

(instance theTalkers of EventHandler
	(properties
		name	"talkers"
	)
)


(class Sounds kindof EventHandler
	;;; The Sounds list contains all the sounds in the game.

;;;	(methods
;;;		pause
;;;	)

	(method (pause tOrF)
		;
		; Pause each sound in the list that does not have the mNOPAUSE flag set

		(self eachElementDo: #perform mayPause (if argc tOrF else TRUE))
	)
)



(instance mayPause of Code
	;; pause each sound in the list that 
	;; does not have the mNOPAUSE flag set

	(method (doit theSound tOrF)
		(if (not (& (theSound flags?) mNOPAUSE))
			(theSound pause: tOrF)
		)
	)
)


(instance theRegions of EventHandler
	(properties
		name "regions"
	)
)


(instance theTimers of Set
	(properties
		name "timers"
	)
)


(instance theMouseDownHandler of EventHandler
	(properties
		name	"mouseDownHandler"
	)
)


(instance theKeyDownHandler of EventHandler
	(properties
		name	"keyDownHandler"
	)
)


(instance theDirectionHandler of EventHandler
	(properties
		name	"directionHandler"
	)
)


(instance theWalkHandler of EventHandler
	(properties
		name	"walkHandler"
	)
)


(instance theExtMouseHandler of EventHandler
	(properties
		name	"extMouseHandler"
	)
)


(instance addToObstaclesCode of Code
	(properties
		name	"aTOC"
	)

	(method (doit thePV &tmp dX dY)
		(if (not (& (thePV signal?) ignrAct))
			(= dX (+ (ego xStep?) (/ (CelWide (ego view?) facingSouth 0) 2)))
			(= dY (* (ego yStep?) 2))
			(curRoom addObstacle:
				((Polygon new:)
					init:	
						;; left top
						(- (thePV brLeft?) dX) 

						;; right top
						(+ (thePV brRight?) dX) 

						;; right bottom
						(+ (thePV brRight?) dX) 
						(+ (thePV y?) dY)

						;; left bottom
						(- (thePV brLeft?) dX) 
						(+ (thePV y?) dY),

					yourself:
				)
			)
		)
	)
)


(instance theWaitCursor of Cursor
	(properties
		view		HAND_CURSOR
	)
)

(instance theNormalCursor of Cursor
	(properties
		view		ARROW_CURSOR
	)
)


(class Game kindof Object
	;; The Game class implements the game which is being written.  The
	;; game author creates a source file with script number 0 which
	;; contains the instance of the class Game which is the game.  This
	;; instance is where, for example, input not handled by any Actor,
	;; Room, Region, etc. will be handled.  

	(properties
		script			NULL			;a current script for the game as a whole
		printLang		ENGLISH
		_detailLevel	3
		panelObj			0				;object of pending control panel function
		panelSelector	0				;selector of pending control panel function
		handsOffCode	NULL			;points to an instance of code for handsOff
		handsOnCode		NULL			;points to an instance of code for handsOn
	)
;;;	(methods
;;;		play				;start playing the game
;;;		replay			;start playing from a restore
;;;		newRoom			;change rooms
;;;		startRoom		;initialize the room which is being changed to
;;;		restart			;restart the game
;;;		restore			;restore a game
;;;		save				;save a game
;;;		changeScore		;change the game score
;;;		handleEvent		;handle user events
;;;		showMem			;show the free memory
;;;		setCursor		;set the cursor shape
;;;		notify			;communication mechanism between Game, Regions, and Rooms
;;;		setScript		;set the script for the Game
;;;		cue				;cues the game script
;;;		quitGame			;stops theGame
;;;		masterVolume	;set volume
;;;		detailLevel		;set _detailLevel and send checkDetail to cast
;;;		pragmaFail		;invoked when nobody responds to the user's input
;;;		handsOff			;remove control from the user
;;;		handsOn			;restore control to the user
;;;		getDisc			;prompts user to insert appropriate CD
;;;	)

	(method (play)
		;
		; Invoked from the kernel, this starts the game going, then goes into the
		; main game loop of doit: then wait for the next animation cycle
		
		;EO: "MonoOut" not recognized by SCICompanion
;(MonoOut {Play 1})
		(= theGame self)
;(MonoOut {Play 2})
		(= curSaveDir (String new:))
;(MonoOut {Play 3})
		(= sysLogPath (String new:))
;(MonoOut {Play 4})
		(curSaveDir data: (Save SGGetSaveDir))	;EO: "Save" was "SaveGame"
;(MonoOut {Play 5})

		; Setup the cursors
		(= normalCursor theNormalCursor)
		(= waitCursor theWaitCursor)

		; Put up the 'wait a bit' cursor while initializing the game
		(self 
			setCursor: waitCursor,
			init:
		)

		; The main game loop
		(while (not quit)
			(self doit:)
		)
	)

	(method (quitGame tOrF)
		(if (or (not argc) tOrF)
			(= quit TRUE)
		)
	)

	(method (masterVolume newVol)
		;
		; Return old masterVolume setting and set it to newVol if present

		(return
			(if argc
				(DoSound SndMasterVol newVol)
			else
				(DoSound	SndMasterVol)
			)
		)
	)

	(method (detailLevel theLevel)
		(if argc
			(= _detailLevel theLevel)
			(planes eachElementDo: #checkDetail theLevel)
		)
		(return _detailLevel)
	)

	(method (replay &tmp theStyle aPlane aList obj node1 node2 node3)
		;
		; Invoked from the kernel, this restarts the game from a restore

		; Make sure all the screen items are added to their planes again,
		;	if they were in the silist originally
		(for	((= node1 (List LFirstNode (planes elements?))))
				node1
				((= node1 (planes nextNode?)))
			(planes nextNode: (List LNextNode node1))
			(= aPlane (List LNodeValue node1))
			(AddPlane aPlane)

			; For each cast in the plane's casts list...
			(for	((= node2 (List LFirstNode ((aPlane casts?) elements?))))
					node2
					((= node2 ((aPlane casts?) nextNode?)))
				((aPlane casts?) nextNode: (List LNextNode node2))
				(= aList (List LNodeValue node2))

				; For each object in the cast list...
				(for	((= node3 (List LFirstNode (aList elements?))))
						node3
						((= node3 (aList nextNode?)))
					(aList nextNode: (List LNextNode node3))
					(= obj (List LNodeValue node3))
					(if (& (obj -info-?) IN_SILIST)
						(AddScreenItem obj)
					)
				)
			)
		)

		; Dispose the event which triggered the save-game which we're restoring
		(if lastEvent
			(lastEvent dispose:)
		)

		; Draw the picture in the game being restored
		(theGame setCursor: waitCursor TRUE)
		(= theStyle
			(if (not (OneOf (curRoom style?) -1 SHOW_SCROLL_LEFT SHOW_SCROLL_RIGHT SHOW_SCROLL_UP SHOW_SCROLL_DOWN))
				(curRoom style?)
			else
				SHOW_PLAIN
			)
		)

		; Figure out what cursor to display
		(cond
			; We're hands off
			((and	(not (user canControl?))
					(not (user canInput?))
				)
				(theGame setCursor: waitCursor)
			)
			; The iconbar's current icon's cursor
			((and theIconBar (theIconBar curIcon?))
				(theGame setCursor: (theIconBar getCursor:))
			)
			(else
				(theGame setCursor: normalCursor)
			)
		)

		; Redisplay the status line
		(StatusLine doit:)

		; Turn sound back on
		(DoSound SndRestore)
		(Sound  pause: FALSE)

		; Adjust the game's real-time counter
		(= tickOffset (- gameTime (GetTime)))

		; The main game loop
		(while (not quit)
			(self doit:)
		)
	)

	(method (init)
		;
		; This initializes the generic game system.  The game's 0 module will be
		;	responsible for modifying this to select and start the initial room
		;	of the game.

		; Make sure some important modules are loaded in.
		Motion
		Sound

		; Initialize the coordinate system
		(= lastScreenX	(- screenWidth	 1) )
		(=	lastScreenY	(- screenHeight 1) )

		; Initialize the Collections
		((= cast theCast)										add:)
		((= features theFeatures) 							add:)
		((= sounds 		Sounds) 								add:)
		((= regions 	theRegions) 						add:)
		((= timers 		theTimers) 							add:)
		((= theDoits 	demons) 								add:)
		((= prints		thePrints)							add:)
		((= planes		thePlanes)							add:)
		((= talkers		theTalkers)							add:)
		((= mouseDownHandler theMouseDownHandler)		add:)
		((= keyDownHandler theKeyDownHandler)			add:)
		((= directionHandler theDirectionHandler)		add:)
		((= walkHandler theWalkHandler)					add:)
		((= extMouseHandler theExtMouseHandler)		add:)
		
		; Set the current save/restore directory
		(Save SGGetSaveDir (curSaveDir data?))	;EO: "Save" was "SaveGame"

		; Initialize the user.  If not specifically set by the application, use
		;	the normal system User.
		(if (not user)
			(= user User)
		)
		(user init:)

		; Create the default plane
		((= thePlane (Plane new:))
			priority:	2,
			init:			0 0 lastScreenX lastScreenY,
			addCast:		cast
		)
		(cast plane: thePlane)

		(Styler init:)	; creates array
		(self setCursor: normalCursor TRUE)
	)

	(method (doit &tmp event pO pS)
		;
		; This is the code which is repeatedly executed in order to run the game

		; If we've got a control panel function pending, do it immediately
		(if panelObj
			(= pO panelObj)
			(= pS panelSelector)
			(= panelObj (= panelSelector 0))
			(Eval pO pS)
		)

		(= gameTime (+ tickOffset (GetTime)))

		; Check all sounds and timers for completion, which will cue if required
		(sounds eachElementDo: #check:)
		(timers eachElementDo: #doit:)

		; See if there's an unattached robot that needs maintenance
		(if autoRobot
			(autoRobot doit:)
		)

		; Give any control panels their chance
		(if panels (panels eachElementDo: #doit) )

		; Give each character in the cast the chance to do its thing.  Show the
		;	changes on the screen, then delete any cast members who are scheduled
		;	for deletion.
		(cast doit:)
		(FrameOut)

		; Check for user input, unless a room change is in progress
		(if (== newRoomNum curRoomNum)
			(user doit:)
		)

		; If we have any pending Cue objects, process them
		(if cuees
			(cuees eachElementDo: #doit)
			(if (cuees isEmpty:)
				(cuees dispose:)
				(= cuees 0)
			)
		)

		; Execute any script attached to the game
		(if script
			(script doit:)
		)

		; Now give each region a chance.
		(regions eachElementDo: #doit:)

		; Miscellaneous objects that need doits
		(theDoits doit:)

		; If somebody wants us to change rooms, they set newRoomNum to do so
		(if (!= newRoomNum curRoomNum)
			(self newRoom:newRoomNum)
		)

		; Remove any expired timers.
		(timers eachElementDo: #delete:)

		(Save SGGameIsRestarting FALSE)	;EO: "Save" was "SaveGame"
	);Game doit

	(method (newRoom n &tmp mX mY theMover theEgo oldCur evt eStyle)
		;
		; Change rooms to room 'n'

		; Get rid of any talkers
		(if (talkers size?)
			(messager cue: TRUE)
		)

		; Show the exit picture, if any
		(if (and	curRoom
					(!= (= eStyle (curRoom exitStyle?)) -1)
			)
			(Styler
				plane:	(curRoom plane?),
				back:		(cond
								((& eStyle SHOW_BLACKOUT)
									0
								)
								((& eStyle SHOW_WHITEOUT)
									7
								)
								(else
									0 ;;force exitStyle (Styler back?)
								)
							),
				style:	(& eStyle $00ff),
				doit:
			)
			(FrameOut)
			;;; Reset Styler for entry showStyle
			(Styler back: -1)
		)
		
		; Turn off cycling
		(PalCycle PalCycOff)

		; Turn off remapping
		(RemapColors RemapOff)

		; Dispose of features
		(features
			eachElementDo: #dispose:,
			release:
		)
		
		; Dispose the cast and expired timers
		(cast eachElementDo: #dispose:)
		(timers eachElementDo: #delete:)

		(regions
			eachElementDo: #perform: DisposeNonKeptRegion,
			release:
		)

		; Get rid of any miscellaneous doit-demoning nodes
		(theDoits
			release:
		)

;;;#ifdef MAC
;;;		(Platform PlatMac PurgeMem)
;;;#endif

		; Do some room number bookkeeping.
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)

		;Load Resources for new room
		(NewRoom newRoomNum)

		; Start up the room to which we're going
		(self startRoom: curRoomNum)

		; Eat all mice downs and mice ups
		(while ((= evt (Event new: (| mouseDown mouseUp))) type?)
			(evt dispose:)
		)
		(evt dispose:)
	);Game newRoom

	(method (startRoom roomNum)
		;
		; Initialize a new room.  Regions should be initialized in this method in
		;	the instance of Game, so that the Region is loaded into the heap below
		;	the rooms in the Region.

		; This allows us to break when the heap is as free as it gets with
		; the game running, letting us detect any fragmentation in the heap
		(if debugOn
			(Dummy)	;EO: Was "SetDebug"
		)

		; Initialize the new room and add it to the front of the region list
		(regions	addToFront: (= curRoom (ScriptID roomNum)))

		; Purge memory that the room requires
		(Purge (curRoom purge?))
		(curRoom init:)
	)

	(method (handleEvent event)
		(cond
			((event claimed?)
				TRUE
			)
			((and script
					(script handleEvent: event)
				)
				TRUE
			)
			((& (event type?) userEvent)
				(self pragmaFail:)
			)
		)
		(return (event claimed?))
	)

	(method (changeScore delta)
		;
		; Update the game score and status line if appropriate

		(+= score delta)
		(StatusLine doit:)
	)

	(method (restart)
		(Save SGRestart)	;EO: "Save" was "SaveGame"
	)

	(method (save &tmp comment num oldCur buf1 buf2 str)
		;
		; Save the game at its current state.  The user interface work for this
		;	is done in class Save, the actual save in the SaveGame SGSave kernel function

		(= comment (String new:))
;;;#ifdef MAC
;;;		(SaveGame SGSave name num (comment data?) (String StrGetData version))
;;;#else
		; Ensure a valid directory
		(if (not (FileIO FileValidPath (String StrGetData curSaveDir)))
			(= str (String new:))
			(= buf1 (String new:))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 (buf1 data?))
			(str format: buf1 curSaveDir)
			(Print
				font: 			999,
				fore:				0,
				back:				(Palette PalMatch 127 127 127),
				addText:			str,
				addButtonBM:	-1 0 0 1 {OK} 0 30,
				init:
			)
			(GetDirectory curSaveDir)
			(str  dispose:)
			(buf1 dispose:)
		)

		(Load RES_FONT smallFont)
		(ScriptID SAVE)

		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)

		(= num (Save doit: comment))
		(switch num
			(RET_ESC
				; User cancelled
			)
			(RET_NO_ACTIVE
				; Error reading .CAT file
				(= buf1 (String new:))
				(= buf2 (String new:))
				(Message MsgGet GAME N_CAT_READ_ERROR NULL NULL 1 (buf1 data?))
				(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
				(if
					(Print
						font: 			999,
						fore:				0,
						back:				(Palette PalMatch 127 127 127),
						addText:			buf1,
						addButtonBM:	SAVE 0 0 FALSE buf2 0 40,
						addButtonBM:	SAVE 2 0 TRUE {Change Dir} 55 40,
						init:
					)
					(GetDirectory curSaveDir)
				)
				(buf1 	dispose:)
				(buf2 	dispose:)
			)
			(else

				;ensure proper CD if appropriate
				(if numCD (self getDisc: numCD) )

				(= oldCur (self setCursor: waitCursor TRUE))
	 			(if (not (Save SGSave name num (comment data?) (String StrGetData version)))	;EO: "Save" was "SaveGame"
					(= buf1 (String new:))
					(= buf2 (String new:))
					(Message MsgGet GAME N_SAVE_ERROR NULL NULL 1 (buf1 data?))
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
					(Print
						font: 			999,
						fore:				0,
						back:				(Palette PalMatch 127 127 127),
						addText:			buf1,
						addButtonBM:	-1 0 0 1 buf2 0 40,
						init:
					)
					(buf1 	dispose:)
					(buf2 	dispose:)
				)
				(self setCursor: oldCur (HaveMouse))
			)
		)
		(Sound  pause: FALSE)
;;;#endif
		(comment dispose:)
	)

	(method (restore &tmp num oldCur buf1 buf2 str aPlane aList obj bit
								node1 node2 node3)
		;
		; Restore a previously saved game.  The user interface work for this is
		;	done in class Restore, the actual save in the SaveGame SGRestore kernel
		;	function

		(= buf1 (String new:))
		(= buf2 (String new:))
		(= str (String new:))

;;;#ifdef MAC
;;;		(SaveGame SGRestore name num version)
;;;#else
		; Ensure we have a valid directory
		(if (not (FileIO FileValidPath (String StrGetData curSaveDir)))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 (buf1 data?))
			(str format: buf1 curSaveDir)
			(Print
				font: 			999,
				fore:				0,
				back:				(Palette PalMatch 127 127 127),
				addText:			str,
				addButtonBM:	-1 0 0 1 {OK} 0 30,
				init:
			)
			(GetDirectory curSaveDir)
		)

		(Load RES_FONT smallFont)
		(ScriptID SAVE)

		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)

		(= num (Restore doit: &rest))
		(if (!= num -1)
			; First make sure all the screen items are deleted from their planes
			(for	((= node1 (List LFirstNode (planes elements?))))
					node1
					((= node1 (planes nextNode?)))
				(planes nextNode: (List LNextNode node1))
				(= aPlane (List LNodeValue node1))

				; For each cast in the plane's casts list...
				(for	((= node2 (List LFirstNode ((aPlane casts?) elements?))))
						node2
						((= node2 ((aPlane casts?) nextNode?)))
					((aPlane casts?) nextNode: (List LNextNode node2))
					(= aList (List LNodeValue node2))

					; For each object in the cast list...
					(for	((= node3 (List LFirstNode (aList elements?))))
							node3
							((= node3 (aList nextNode?)))
						(aList nextNode: (List LNextNode node3))
						(= obj (List LNodeValue node3))
						(= bit (& (obj -info-?) IN_SILIST))
						(if bit
							(DeleteScreenItem obj)
							(obj -info-: (| (obj -info-?) bit))
						)
					)
				)
				(DeletePlane aPlane)
			)

			(self setCursor: waitCursor TRUE)

			(if (Save SGCheckSaveGame name num (String StrGetData version))	;EO: "Save" was "SaveGame"

				;Acquire appropriate CD in drive
				(self getDisc: (CD GetSaveCD))

				(Save SGRestore name num version)	;EO: "Save" was "SaveGame"
			else
				; Make sure all the screen items are added to their planes again,
				;	if they were in the silist originally
				(for	((= node1 (List LFirstNode (planes elements?))))
						node1
						((= node1 (planes nextNode?)))
					(planes nextNode: (List LNextNode node1))
					(= aPlane (List LNodeValue node1))
					(AddPlane aPlane)

					; For each cast in the plane's casts list...
					(for	((= node2 (List LFirstNode ((aPlane casts?) elements?))))
							node2
							((= node2 ((aPlane casts?) nextNode?)))
						((aPlane casts?) nextNode: (List LNextNode node2))
						(= aList (List LNodeValue node2))

						; For each object in the cast list...
						(for	((= node3 (List LFirstNode (aList elements?))))
								node3
								((= node3 (aList nextNode?)))
							(aList nextNode: (List LNextNode node3))
							(= obj (List LNodeValue node3))
							(if (& (obj -info-?) IN_SILIST)
								(AddScreenItem obj)
							)
						)
					)
				)
				(Message MsgGet GAME N_WRONG_INTERP NULL NULL 1 (buf1 data?))
				(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
				(Print
					font:				999,
					fore:				0,
					back:				(Palette PalMatch 127 127 127),
					addText:			(buf1 data?),
					addButtonBM: 	-1 0 0 1 (buf2 data?) 0 40,
					init:
				)
				(self setCursor: oldCur (HaveMouse))
			)
		)
		(Sound pause: FALSE)
;;;#endif

		(buf1 dispose:)
		(buf2 dispose:)
		(str  dispose:)
	)

	(method (setCursor form showIt theX theY hotX hotY &tmp oldCur)
		;
		; Set the cursor form, returning the previous form
		; form MUST be an object

		(= oldCur theCursor)
		(= theCursor form)
		(form init:)
		(if (> argc 1)
			(if showIt
				(theCursor show:)
			else
				(theCursor hide:)
			)
			(if (> argc 2)
				(SetCursor theX theY)
				(if (> argc 4)
					(SetCursor form 0 0 hotX hotY)
				)
			)
		)
		(return oldCur)
	)

	(method (showMem &tmp buffer)
		;
		; Display information about free hunk memory

		(= buffer (String new:))
		(buffer format:
			{Free Memory: 	%u kBytes}
			(MemoryInfo MemLargest)
		)
		(Print
			addText: buffer,
			init:
		)
		(buffer dispose:)
	)

	(method (pragmaFail)
		;
		; This method is called when no one in the cast, features,
		;	regions, handlers, or the Game responds to an event
	)

	(method (notify)
		;
		; Handle arbitrary communication between Game, Regions, and Rooms.
		; Protocol and number of parameters are up to the game programmer.
	)

	(method (setScript newScript)
		;
		; Attach a new script to this object, removing any existing one
		
		(if script
			(script dispose:)
		)
		(if (and argc newScript)
			(newScript init: self &rest)
		)
	)

	(method (cue)
		(if script
			(script cue: &rest)
		)
	)
	(method (handsOff)
		(if handsOffCode
			(handsOffCode doit: &rest)
		else
			(User
				canControl:	FALSE,
				canInput:	FALSE
			)
			(if ego
				(ego setMotion: 0)
			)
		)
	)
	(method (handsOn)
		(if handsOnCode
			(handsOnCode doit: &rest)
		else
			(User 
				canControl:	TRUE,
				canInput:	TRUE
			)
		)
	)
	(method (getDisc n &tmp ret)
		(= numCD n)	; system global
		(while 	(and 
						(!= -1 (= ret (CD Check n)) ); returns -1 if file-based
						(!= ret n)
					)
			(Printf {Please insert CD #%d then hit any key.} n)
		)
		(return ret)
	)
);class Game



(class Region kindof Object
	;;; A Region is an area of a game which is larger than a Room and which
	;;; has global actions associated with it.  Music which needs to be played
	;;; across rooms needs to be owned by a Region so that it is not disposed
	;;; on a room change.

	(properties
		name 			"Rgn"
		script 		0		;the ID of a script attached to the Region
		number 		0		;the module number of the Region
		modNum		-1		;module for our messages
		noun			0		;noun associated with us
		case			0		;case associated with us
		timer 		0		;the ID of a timer attached to the Region
		keep 			0		;0->dispose Region on newRoom:, 1->keep Region on newRoom:
		initialized 0		;has the Region been initialized?
	)
;;;	(methods
;;;		handleEvent		;handle user input
;;;		doVerb			;respond to a given verb
;;;		setScript		;set the script for this Region
;;;		cue				;cue the Region
;;;		newRoom			;invoked when the Game changes rooms
;;;		notify			;communication mechanism between Game, Regions, and Rooms
;;;	)

	(method (init)
		;
		; Initialize the Region.  Region initialization is controlled by the
		;	'initialized' property, so that the Region is only initialized once,
		;	upon entry, not each time rooms are changed

		(if (not initialized)
			(= initialized TRUE)
			(if (not (regions contains: self))
				(regions addToEnd: self)
			)
			(super init:)
		)
	)

	(method (doit)
		(if script
			(script doit:)
		)
	)

	(method (handleEvent event)
		(cond
			((event claimed?)
				TRUE
			)
			((& (event type?) direction)
				FALSE
			)
			((not	(and	script	
							(or	(script handleEvent: event)
									TRUE
							)
							(event claimed?)	;??? BKH
					)
				)
				(event claimed:
					(self doVerb: (event message?))
				)
			)
		)
		(return (event claimed?))
	)

	(method (doVerb theVerb &tmp who)
		(if (and
				(== modNum -1)
				(== (= modNum (curRoom modNum?)) -1)
			 )
			(= modNum curRoomNum)
		)
		(return
			(if (Message MsgGet modNum noun theVerb case 1)
				(messager say: noun theVerb case NULL NULL modNum)
				TRUE
			else
				FALSE
			)
		)
	)

	(method (dispose)
		;
		; Delete this region from the region list, then dispose any objects
		;	attached to/owned by it

		(regions delete: self)
		(if script
			(script dispose:)
		)
		(if timer
			(timer dispose:, delete:)
		)
		(sounds eachElementDo: #clean: self)

		; Remove the Region module from the heap.
		(DisposeScript number)
	)

	(method (setScript newScript)
		;
		; Attach a new script to this object, removing any existing one
		
		(if script
			(script dispose:)
		)
		(if (and argc newScript)
			(newScript init: self &rest)
		)
	)

	(method (cue)
		(if script
			(script cue:)
		)
	)

	(method (newRoom n)
	)

	(method (notify)
		;; Handle arbitrary communication between Game, Regions, and Rooms.
		;; Protocol and number of parameters are up to the game programmer.
	)
)



(class Room kindof Region
	(properties
		picture 			-1		;number of picture for this Room
		plane				0		;pointer to plane for our pic
		style				-1	 	;the style in which to draw this Room's picture
		exitStyle		-1		;the style in which to "undraw" this room's picture
		horizon 			0		;y coordinate of Room's horizon
		controls 		0		;a list of controls (buttons, etc.) in the Room
		north 			0	 	;module number of Room to the north
		east 				0	 	;module number of Room to the east
		south 			0	 	;module number of Room to the south
		west 				0	 	;module number of Room to the west
		curPic 			0	 	;picture number of currently visible picture
		purge				500 	;memory to purge upon loading module (in K)
		
		picAngle			0		;how far from vertical is our view? 0-89
		vanishingX		160
		vanishingY		0

		obstacles  		0		;pointer to	a list of Polygons defining obstacles
		inset				0		;current inset if any

		edgeN				40				;\
		edgeE				319			; edge hits for this room
		edgeW				0				;
		edgeS				189			;/
	)
;;;	(methods
;;;		handleEvent		;handle user input
;;;		setRegions		;set the Regions which contain this Room
;;;		drawPic			;draw the picture for this Room
;;;		overlay			;overlay a picture
;;;		addObstacle		;add a Polygon or Feature to the obstacle list
;;;		reflectPosn		;reposition (user alterEgo?) after room change
;;; 		edgeToRoom		;return 0 or number of an adjacent room
;;;		roomToEdge		;return 0 or edge value leading to this room
;;;		setInset			;to set up an inset
;;;	)

	(method (init)
		(= number curRoomNum)
		(= perspective picAngle)

		; If no plane is specified, use the default.
		(if (not plane)
			(= plane thePlane)
		)
		(self drawPic: picture)

		; Reposition ego if he hit an edge in the previous room
		(self reflectPosn: (user alterEgo?) ((user alterEgo?) edgeHit?))
		((user alterEgo?) edgeHit: 0)
	)

	(method (reflectPosn theActor theEdge)
		;
		; Reposition the actor on the screen, depending on which side he is

		(switch theEdge
			(NORTH
				(theActor y: (- (curRoom edgeS?) 1))
			)
			(WEST
				(theActor x: (- (curRoom edgeE?) (theActor xStep?)))
			)
			(SOUTH
				(theActor y: (+ horizon (theActor yStep?)))
			)
			(EAST
				(theActor x: (+ (curRoom edgeW?) 1))
			)
		)
	)

	(method (doit &tmp nRoom)
		;
		; Send the doit: to any script, then check to see if ego has hit the edge
		;	of the screen

		(if script
			(script doit:)
		)
		(if (= nRoom (self edgeToRoom: ((user alterEgo?) edgeHit?)))
			(self newRoom: nRoom)
		)
	)

	(method (edgeToRoom edge)
		;
		; Return the room number of the adjacent room or 0

		(return
			(switch edge
				(NORTH	north)
				(EAST		east)
				(SOUTH	south)
				(WEST		west)
			)
		)
	)

	(method (roomToEdge rm)
		;
		; Return edge that leads to this room or 0
		(return
			(switch rm
				(north	NORTH)
				(south	SOUTH)
				(east		EAST)
				(west		WEST)
			)
		)
	)

	(method (dispose)
		(if obstacles
			(obstacles dispose:)
		)
		; If we've got our own plane, dispose of it
		(if (!= plane thePlane)
			(plane dispose:)
			(= plane 0)
		)
		(super dispose:)
	)

	(method (handleEvent event)
		(or
			(if inset
				(inset handleEvent: event)
			)
			(super handleEvent: event)
		)
		(return (event claimed?))
	)

	(method (setInset theInset who reg delayDraw)
		(if inset
			(inset dispose:)
			(= inset 0)
		)
		(if (and argc theInset)
			(theInset init: 
				(if (>= argc 2) who else 0) 
				self 
				(if (>= argc 3) reg else 0)
				(if (>= argc 4) delayDraw else 0)
			)
		)
	)

	(method (setRegions region &tmp i n regID)
		;
		; Set the regions used by a room

		(for	((= i 0))
				(< i argc)
				((++ i))
			(= n [region i])
			(= regID (ScriptID n))
			(regID number: n)
			(regions add: regID)
			(if (not (regID initialized?))
				(regID init:)
			)
		)
	)

	(method (addObstacle	obstacle)
		;
		; Add a polygon to the obstacles list

		(if (not obstacles)
			(= obstacles (List new:))
		)
		(obstacles add: obstacle &rest)
	)

	(method (newRoom n)
		;
		; Remove this Room from the regions, let the rest of the regions know
		;	about the room change, then put ourselves back in the action

		(regions
			delete: 			self,
			eachElementDo: #newRoom: n,
			addToFront: 	self
		)

		(= newRoomNum n)
		(super newRoom: n)
	)

	(method (drawPic pic theStyle setProps)
		;
		; Draw the given picture in the appropriate style.  If 'setProps'
		;	is passed and TRUE, the 'pic' and 'theStyle' will be set into
		;	the room's picture and style properties (unless they are -1).

		(if (not plane)
			((= plane (Plane new:))
				init:
			)
		)

		(if (and (> argc 2) setProps)
			(if (!= pic -1)
				(= picture (= curPic pic))
			)
			(if (!= theStyle -1)
				(= style theStyle)
			)
		)

		(plane drawPic: pic
			(cond
				((== style -1)
					SHOW_PLAIN
				)
				((> argc 1)
					theStyle
				)
				(else
					style
				)
			)
		)
	)

	(method (overlay pic theStyle)
		;
		; Overlay the current picture with another

	)
)



(class StatusLine kindof Object
	;;; The StatusLine class provides a status line at the top of the
	;;; screen which is programmer-definable.  When enabled, it overlays
	;;; the menu bar.  The user may still access the menu by pressing Esc
	;;; or positioning the mouse pointer in the status line end pressing
	;;; the mouse button.  The status line usually shows the player's
	;;; score.
	;;; To use a status line in a game, create an instance of class Code
	;;; whose doit: method takes a pointer to an array.  The Code should
	;;; format the desired text string into the array.
	;;; To display the status line, execute (StatusLine enable:).

	(properties
		name 		"SL"
		state 	FALSE		;enabled/disabled
		code 		0			;ID of Code to display status line
	)
;;;	(methods
;;;		enable			;display the status line
;;;		disable			;hide the status line
;;;	)

	(method (doit &tmp theLine)
		;
		; This method calls the application code to format the status line string
		;	at theLine, then draws it

		(if code
			(= theLine (String newWith: 150 {}))
			(code doit: theLine)
			(theLine dispose:)
		)
	)

	(method (enable)
		;
		; Display the status line

		(= state TRUE)
		(self doit:)
	)

	(method (disable)
		;
		; Hide the status line

		(= state FALSE)
		(self doit:)
	)
)





(instance DisposeNonKeptRegion of Code
	;;; Used during room changes to dispose any Regions whose 'keep' property
	;;; is not TRUE.

	(properties
		name "DNKR"
	)


	(method (doit region)
		(if (not (region keep?))
			(region dispose:)
		)
	)
)
