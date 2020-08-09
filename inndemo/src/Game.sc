;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GAME.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated:
;;;;		Brian K. Hughes
;;;;		August 19, 1992
;;;;
;;;;	This module contains the classes which implement much of the behavior
;;;;	of an adventure game.
;;;;
;;;;	Classes:
;;;;		Sounds
;;;;		Cue
;;;;		Game
;;;;		Region
;;;;		Room
;;;;		StatusLine
;;;;
;;;;	Procedures
;;;;		PromptForDiskChange


(script#	GAME)
(include game.sh) (include "994.shm")
(use Main)
(use Print)
(use Polygon)
(use Sound)
(use Save)
(use Motion)
(use Invent)
(use User)
(use System)

;;;(procedure
;;;	PromptForDiskChange
;;;)
;;;
;;;(extern
;;;	GetDirectory	SAVE	0
;;;)



(instance theCast of EventHandler
	(properties
		name "cast"
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


(instance theAddToPics of EventHandler
	(properties
		name "addToPics"
	)
	(method (doit)
		;
		; Call kernel to draw the current list of addToPics
		; They will not be seen until the next Animate call

		(self eachElementDo: #perform: addToObstaclesCode)
		(AddToPic elements)
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



(class Cue kindof Object
	;;; This class provides a way of delaying a cue until the beginning of the
	;;;	next cycle.  Dynamic instances of Cue can be put on the cuees list

	(properties
		cuee		0		; who to cue
		cuer		0		; who's doin' the cuein'
		register	0		; value to pass to cue
	)
	(method (doit)
		(cuees delete: self)
		(if (cuees isEmpty:)
			(cuees dispose:)
			(= cuees 0)
		)
		(cuee cue: register cuer)
		(self dispose:)
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
						(- (CoordPri PTopOfBand (CoordPri (thePV y?))) dY)

						;; right top
						(+ (thePV brRight?) dX) 
						(- (CoordPri PTopOfBand (CoordPri (thePV y?))) dY)

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
;;;	)

	(method (play)
		;
		; Invoked from the kernel, this starts the game going, then goes into the
		; main game loop of doit: then wait for the next animation cycle

		(= theGame self)
		(= curSaveDir (GetSaveDir))

		; Put up the 'wait a bit' cursor while initializing the game
		(self 
			setCursor: waitCursor TRUE,
			init:
		)

		; This can't be in the same message as above because normalCursor gets
		;  evaluated first, making it impossible to initialize it in the game's
		;	init method
		(self setCursor: normalCursor TRUE)
		
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
				(DoSound	MasterVol newVol)
			else
				(DoSound	MasterVol)
			)
		)
	)

	(method (detailLevel theLevel)
		(if argc
			(= _detailLevel theLevel)
			(cast eachElementDo: #checkDetail)
		)
		(return _detailLevel)
	)

	(method (replay &tmp theStyle)
		;
		; Invoked from the kernel, this restarts the game from a restore

		; Dispose the event which triggered the save-game which we're restoring
		(if lastEvent
			(lastEvent dispose:)
		)

		; Dispose any modeless dialog present
		(if modelessDialog
			(modelessDialog dispose:)
		)

		; Invalidate any saved background bitmaps which were in the game
		;	being restored
		(cast eachElementDo: #perform: RestoreUpdate)

		; Draw the picture and put in all the addToPics which were in the game
		;	being restored
		(theGame setCursor: waitCursor TRUE)
		(= theStyle
			(if (not (OneOf (curRoom style?) -1 SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN))
				(curRoom style?)
			else
				PLAIN
			)
		)
		(DrawPic (curRoom curPic?) theStyle TRUE)
		(if (!= overlays -1)
			(DrawPic overlays PLAIN FALSE)
		)

		; Redraw the views that we have saved as addToPics
		(addToPics doit:)

		; Figure out what cursor to display
		(cond
			; We're hands off
			((and	(not (user canControl?))
					(not (user canInput?))
				)
				(theGame setCursor: waitCursor)
			)
			; The iconbar's current icon's cursor
			((and	theIconBar (theIconBar curIcon?))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(else
				(theGame setCursor: normalCursor)
			)
		)

		; Redisplay the status line
		(StatusLine doit:)

		; Turn sound back on
		(DoSound RestoreSound)
		(sounds pause: FALSE)

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

		; Initialize the Collections
		((= cast theCast) 								add:)
		((= features theFeatures) 						add:)
		((= sounds 		Sounds) 							add:)
		((= regions 	theRegions) 					add:)
		((= addToPics 	theAddToPics) 					add:)
		((= timers 		theTimers) 						add:)
		((= theDoits 	demons) 							add:)
		((= mouseDownHandler theMouseDownHandler)	add:)
		((= keyDownHandler theKeyDownHandler)		add:)
		((= directionHandler theDirectionHandler)	add:)
		((= walkHandler theWalkHandler)				add:)
		(= fastCast 	NULL)
		
		; Set the current save/restore directory
		(= curSaveDir (GetSaveDir))

		; Initialize the inventory
		(Inventory init:)

		; Initialize the user.  If not specifically set by the application, use
		;	the normal system User.
		(if (not user)
			(= user User)
		)
		(user init:)
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

		; If there's a fastCast with talkers in it, process it only
		(= gameTime (+ tickOffset (GetTime)))
		(if fastCast 
			(while fastCast 
				(fastCast eachElementDo: #doit)
				(if ((= event (Event new:)) type?)
					(if fastCast
						(fastCast firstTrue: #handleEvent event)
					)
				)
				(event dispose:)
				(= gameTime (+ tickOffset (GetTime)))
				(sounds eachElementDo: #check:)
			)
		)

		; If there's a prints list with prints in it, process it
		(if prints
		  	(prints eachElementDo: #doit)

			; If none of the Print in the list are modeless, then we are locked
			;	in this loop - we need to create our own events and skip the rest
			;	of the doit: code

			(if (not modelessDialog)
		  		(if ((= event (Event new:)) type?)
		  			(if prints
		  				(prints firstTrue: #handleEvent: event)
		  			)
		  		)
		  		(event dispose:)
		  		(= gameTime (+ tickOffset (GetTime)))
				(return)
			)
		)

		; Check all sounds and timers for completion, which will cue if required
		(sounds eachElementDo: #check:)
		(timers eachElementDo: #doit:)
		
		(if (and modelessDialog
					(modelessDialog check:)
			)
			(modelessDialog dispose:)
		)

		; Give each character in the cast the chance to do its thing.  Show the
		;	changes on the screen, then delete any cast members who are scheduled
		;	for deletion.
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue:)
		)

		; If we have any pending Cue objects, process them
		(if cuees
			(cuees eachElementDo: #doit)
		)

		; Execute any script attached to the game
		(if script
			(script doit:)
		)

		; Now give each region a chance.
		(regions eachElementDo: #doit:)

		; If we have a fastCast (as a result of someone's doit bringing up
		;	a message), get out now
		(if fastCast
			(return)
		)

		; Check for user input, unless a room change is in progress
		(if (== newRoomNum curRoomNum)
			(user doit:)
		)

		; Miscellaneous objects that need doits
		(theDoits doit:)

		; If somebody wants us to change rooms, they set newRoomNum to do so
		(if (!= newRoomNum curRoomNum)
			(self newRoom:newRoomNum)
		)

		; Remove any expired timers.
		(timers eachElementDo: #delete:)

		(GameIsRestarting FALSE)
	);Game doit

	(method (newRoom n &tmp mX mY theMover theEgo oldCur evt)
		;
		; Change rooms to room 'n'

		; Dispose of any addToPics
		(addToPics
			eachElementDo: #dispose:,
			eachElementDo:	#delete:,
			release:
		)
		
		; Dispose of features
		(features
			eachElementDo: #perform: featureDisposeCode,
			release:
		)
		
		; Dispose the cast, expired timers, and non-kept regions
		(cast
			eachElementDo: #dispose:,
			eachElementDo: #delete:
		)
		(timers eachElementDo: #delete:)
		(regions
			eachElementDo: #perform: DisposeNonKeptRegion,
			release:
		)

		; Get rid of any miscellaneous doit-demoning nodes
		(theDoits
			release:
		)

		; Dispose lastCast (internal kernel knowledge of the cast during
		;	the previous animation cycle)
		(Animate 0)

		; Do some room number bookkeeping.
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)

		; If resource usage tracking is enabled, flush all non-purgable resources
		(FlushResources n)

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
			(SetDebug)
		)

		; Initialize the new room and add it to the front of the region list
		(regions	addToFront: (= curRoom (ScriptID roomNum)))
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
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(RestartGame)
	)

	(method (save &tmp [comment 20] num oldCur [buf1 100] [buf2 5] [str 100])
		;
		; Save the game at its current state.  The user interface work for this
		;	is done in class Save, the actual save in the SaveGame kernel function

;;;#ifdef MAC
;;;		(SaveGame name num @comment version)
;;;#else
		; Ensure a valid directory
		(if (not (ValidPath curSaveDir))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 @buf1)
			(Format @str @buf1 curSaveDir)
			(Print
				font: 	SYSFONT,
				addText:	@str,
				init:
			)
			(GetDirectory curSaveDir)
		)

		(Load FONT smallFont)
		(ScriptID SAVE)

		(= oldCur (self setCursor: normalCursor))
		(sounds pause: TRUE)

		(if (PromptForDiskChange TRUE)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(= num (Save doit: @comment))
			(if (!= num -1)
				(= oldCur (self setCursor: waitCursor TRUE))
	 			(if (not (SaveGame name num @comment version))
					(Message MsgGet GAME N_DISK_FULL NULL NULL 1 @buf1)
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf2)
					(Print
						font: 		SYSFONT,
						addText:		@buf1,
						addButton:	1 @buf2 0 40,
						init:
					)
				)
				(self setCursor: oldCur (HaveMouse))
			)
			(PromptForDiskChange FALSE)
		)
		(sounds pause: FALSE)
;;;#endif
	)

	(method (restore &tmp [comment 20] num oldCur [buf1 100] [buf2 5] [str 100])
		;
		; Restore a previously saved game.  The user interface work for this is
		;	done in class Restore, the actual save in the RestoreGame kernel
		;	function

;;;#ifdef MAC
;;;		(RestoreGame name num version)
;;;#else
		; Ensure we have a valid directory
		(if (not (ValidPath curSaveDir))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 @buf1)
			(Format @str @buf1 curSaveDir)
			(Print
				font: 	SYSFONT,
				addText:	@str,
				init:
			)
			(GetDirectory curSaveDir)
		)

		(Load FONT smallFont)
		(ScriptID SAVE)

		(= oldCur (self setCursor: normalCursor))
		(sounds pause: TRUE)

		(if (PromptForDiskChange TRUE)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(= num (Restore doit: &rest))
			(if (!= num -1)
				(self setCursor: waitCursor TRUE)

				(if (CheckSaveGame name num version)
					(RestoreGame name num version)
				else
					(Message MsgGet GAME N_WRONG_INTERP NULL NULL 1 @buf1)
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf2)
					(Print
						font:			SYSFONT,
						addText:		@buf1,
						addButton: 	1 @buf2 0 40,
						init:
					)
					(self setCursor: oldCur (HaveMouse))
				)
			)
			(PromptForDiskChange FALSE)
		)
		(sounds pause: FALSE)
;;;#endif
	)

	(method (setCursor form showIt theX theY hotX hotY &tmp oldCur)
		;
		; Set the cursor form, returning the previous form

		(= oldCur theCursor)
		(if (IsObject form)
			(= theCursor form)
			(form init:)
		else
			(SetCursor form 0 0)
		)
		(if (> argc 1)
			(SetCursor showIt)
			(if (> argc 2)
				(SetCursor theX theY)
				(if (> argc 4)
					(SetCursor form 0 0 hotX hotY)
				)
			)
		)
		(return oldCur)
	)

	(method (showMem &tmp [buffer 100])
		;
		; Display information about free heap and hunk memory

		(Format @buffer
			{Free Heap: 	%u Bytes\n
			Largest ptr: 	%u Bytes\n
			FreeHunk: 		%u KBytes\n
			Largest hunk: 	%u Bytes}
			(MemoryInfo FreeHeap)
			(MemoryInfo LargestPtr)
			(>> (MemoryInfo FreeHunk) 6)
			(MemoryInfo LargestHandle)
		)
		(Print
			addText: @buffer,
			init:
		)
	)

	(method (pragmaFail)
		;
		; This method is called when no one in the cast, features, addToPics,
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
		(if newScript
			(newScript init: self &rest)
		)
	)

	(method (cue)
		(if script
			(script cue:)
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
			(if (IsObject ego)
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
		(if (== modNum -1)
			(= modNum curRoomNum)
		)
		(return
			(if (Message MsgGet modNum noun theVerb NULL 1)
				(messager say: noun theVerb NULL NULL NULL modNum)
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
		(if (IsObject script)
			(script dispose:)
		)
		(if (IsObject timer)
			(timer dispose:, delete:)
		)
		(sounds eachElementDo: #clean: self)

		; Remove the Region module from the heap.
		(DisposeScript number)
	)

	(method (setScript newScript)
		;
		; Attach a new script to this object, removing any existing one
		
		(if (IsObject script)
			(script dispose:)
		)
		(if newScript
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
		name 				"Rm"
		picture 			0		;number of picture for this Room
		style 			-1	 	;the style in which to draw this Room's picture
		horizon 			0		;y coordinate of Room's horizon
		controls 		0		;a list of controls (buttons, etc.) in the Room
		north 			0	 	;module number of Room to the north
		east 				0	 	;module number of Room to the east
		south 			0	 	;module number of Room to the south
		west 				0	 	;module number of Room to the west
		curPic 			0	 	;picture number of currently visible picture
		
		picAngle			0		;how far from vertical is our view? 0-89
		vanishingX		160
		vanishingY		0

		obstacles  		0		;pointer to	a list of Polygons defining obstacles
		inset				0		;current inset if any
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

		; Draw a picture (if non zero) in proper style
		(if picture
			(self drawPic: picture)
		)

		; Reposition ego if he hit an edge in the previous room
		(self reflectPosn: (user alterEgo?) ((user alterEgo?) edgeHit?))
		((user alterEgo?) edgeHit: 0)
	)

	(method (reflectPosn theActor theEdge)
		;
		; Reposition the actor on the screen, depending on which side he is

		(switch theEdge
			(NORTH
				(theActor y: (- southEdge 1))
			)
			(WEST
				(theActor x: (- eastEdge (theActor xStep?)))
			)
			(SOUTH
				(theActor y: (+ horizon (theActor yStep?)))
			)
			(EAST
				(theActor x: (+ westEdge 1))
			)
		)
	)

	(method	(doit &tmp nRoom)
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

	(method (setInset theInset who reg)
		(if inset
			(inset dispose:)
		)
		(if (and argc theInset)
			(theInset init: (if (>= argc 2) who else 0) self (if (>= argc 3) reg else 0))
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

		(if (not (IsObject obstacles))
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

	(method (drawPic pic theStyle)
		;
		; Draw the given picture in the appropriate style

		; Dispose of addToPics list that is now invalid
		(if addToPics
			(addToPics
				eachElementDo: #dispose:,
				release:
			)
		)

		(= curPic pic)
		(= overlays -1)
		(DrawPic pic
			(cond
				((== argc 2)		; use style passed
					theStyle
				)
				((!= style -1)		; use default room style
					style
				)
				(else					; use global style
					PLAIN
				)
			)
			TRUE									; clear buffer before drawing
		)
	)

	(method (overlay pic theStyle)
		;
		; Overlay the current picture with another

		(= overlays pic)
		(DrawPic
			pic
			(cond
				((== argc 2)		; use style passed
					theStyle
				)
				((!= style -1)		; use default room style
					style
				)
				(else					; use global style
					PLAIN
				)
			)
			FALSE									; don't clear buffer
		)
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
			(= theLine (Memory MNeedPtr 150))
			(code doit: theLine)
			(DrawStatus (if state theLine else 0))
			(Memory MDisposePtr theLine)
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



(procedure (PromptForDiskChange saveDisk
				&tmp ret [saveDevice 40] [curDevice 40] str 
			  [buf1 40] [buf2 10] [buf3 5])

	(= str (Memory MNeedPtr 150))


	;; Used by restore: to prompt the user to change disks if running
	;; on single-drive removable media.

	(= ret TRUE)
	(DeviceInfo GetDevice curSaveDir @saveDevice)
	(DeviceInfo CurDevice @curDevice)
	(if
		(and
			(DeviceInfo DevRemovable @curDevice)
			(or (DeviceInfo SameDevice @saveDevice @curDevice) 
 				(not (DeviceInfo SaveDirMounted (theGame name?))))
		)

		(Message MsgGet GAME N_INSERT_DISK NULL NULL 1 @buf1)
		(Message MsgGet GAME N_SAVE_DISK NULL NULL 1 @buf2)
		(Message MsgGet GAME N_GAME_DISK NULL NULL 1 @buf3)
		(Format str
			@buf1
			(if saveDisk @buf2 else @buf3)
			@saveDevice
		)

		;Do whatever is necessary to prepare for switching disks.
		(Load FONT userFont)
		(DeviceInfo CloseDevice)
		(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf1)
		(Message MsgGet GAME N_CANCEL_BUTTON NULL NULL 1 @buf2)
		(Message MsgGet GAME N_CHG_DIR_BUTTON NULL NULL 1 @buf3)

		(= ret
			(if saveDisk
				(Print
					font: 		SYSFONT,
					addText:		str,
					addButton: 	TRUE @buf1 0 40,
					addButton: 	FALSE @buf2 30 40,
					addButton: 	2 @buf3,
					init:
				)
			else
				(Print
					font: 		SYSFONT,
					addText:		str,
					addButton: 	TRUE @buf1 0 40,
					init:
				)
			)
		)

		(if (== ret 2)
			(= ret (GetDirectory curSaveDir))
		)

	)
	(Memory MDisposePtr str)
	(return ret)
)



(instance RestoreUpdate of Code
	;;; Used by replay: to properly deal with members of the cast which were
	;;; not updating when the game was saved.

	(properties
		name "RU"
	)

	(method (doit obj &tmp sigBits)
		;; If the object has underBits, it was not updating.  Its underBits
		;; property is now invalid, so clear it.  Also, set the signal bits
		;; to draw the object and stopUpd: it.

		(if (obj underBits?)
			(= sigBits (obj signal?))
			(|= sigBits stopUpdOn)
			(&= sigBits (~ notUpd))
			(obj underBits:0, signal:sigBits)
		)
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



(instance featureDisposeCode	 of Code
	;; Dispose of features and if it's a View	send delete	to it also
	;; since it's really the the delete method that gets rid of a View.
	;; Views will show up in the features list if they have been addToPic'd
	(properties
		name "fDC"
	)

	(method (doit theFeature)
		(if (theFeature respondsTo: #delete:)
			;; it's kindOf a View, make sure it's not added to 
			;; addToPics again, and do a complete disposal
			(theFeature 
				signal:(& (theFeature signal?) (~ viewAdded)), 
				dispose:, 
				delete:
			)
		else
			;; just a feature, dowse it.
			(theFeature dispose:)
		)
	)
)