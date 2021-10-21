;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GAME.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	This module contains the classes which implement much of the behavior
;;;;	of an adventure game.
;;;;
;;;;	Classes:
;;;;		Game
;;;;		Region
;;;;		Room
;;;;		Locale
;;;;		StatusLine


(script#	GAME)
(include game.sh)
(use Main)
(use Intrface)
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



;;;; GAME OBJECTS
;;;; These are static objects which are used in the generalized game.  Game
;;;; specific objects will be defined in module 0.

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
(instance theSortedFeatures of EventHandler
	(properties
		name "sFeatures"
	)
	(method (delete theElement)
		;;need this because SortedAdd creates dynamic temporary lists that
		;;must be released and disposed
		(super delete: theElement)
		(if (and useSortedFeatures	
				(theElement isKindOf: List) ;Collection)
			)
			(theElement release: dispose:)
		)
	)
)
(instance theSounds of EventHandler
	(properties
		name "sounds"
	)
)
(instance theRegions of EventHandler
	(properties
		name "regions"
	)
)
(instance theLocales of EventHandler
	(properties
		name "locales"
	)
)
(instance theAddToPics of EventHandler
	(properties
		name "addToPics"
	)
	;; Call kernel to draw the current list of PicViews
	;; They will not be seen until the next Animate call
	(method (doit)
		(AddToPic elements)
	)
)
;;;(instance roomControls of Controls
;;;	(properties
;;;		name "controls"
;;;	)
;;;)
(instance theTimers of Set
	(properties
		name "timers"
	)
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(class Game of Object
	;; The Game class implements the game which is being written.  The
	;; game author creates a source file with script number 0 which
	;; contains the instance of the class Game which is the game.  This
	;; instance is where, for example, input not handled by any Actor,
	;; Room, Region, etc. will be handled.  

	(properties
		script 0			;a current script for the game as a whole
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
;;;		setSpeed			;set the animation speed
;;;		setCursor		;set the cursor shape
;;;		checkAni			;check the animation speed, dropping out Extras if too bad
;;;		notify			;communication mechanism between Game, Regions, and Rooms
;;;		setScript		;set the script for the Game
;;;		cue				;cues the game script
;;;
;;;		wordFail			;invoked when parser can't find a word in the dictionary
;;;		syntaxFail		;invoked when the parser can't make sense of input
;;;		semanticFail	;invoked when a sentence isn't 'logical'
;;;		pragmaFail		;invoked when nobody responds to the user's input
;;;	)

	

	(method (play)
		;; Invoked from the kernel, this starts the game going, then goes
		;; into the main game loop of doit: then wait for the next animation
		;; cycle.

		(= theGame self)

		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting))
			(GetCWD curSaveDir)
		)

		;Put up the 'wait a bit' cursor while initializing the game.
		(self setCursor: waitCursor TRUE)
		(self init:)
		(self setCursor: normalCursor (HaveMouse))
		
		;The main game loop -- doit:, then wait and doit: again.
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))
		)
	)
	
	
	(method (replay)
		;; Invoked from the kernel, this restarts the game from a restore.

		;Dispose the event which triggered the save-game which we're 
		;restoring.
		(if lastEvent (lastEvent dispose:))
		;(sortedFeatures release:)

		;Dispose any modeless Dialog present when the user selected to
		;restore the game.
		(if modelessDialog
			(modelessDialog dispose:)
		)

		;Invalidate any saved background bitmaps which were in the game
		;being restored.
		(cast eachElementDo: #perform: RestoreUpdate)

		;Draw the picture and put in all the PicViews which were in the game
		;being restored.
		(theGame setCursor:waitCursor TRUE)
		(DrawPic (curRoom curPic?) WIPEDOWN)
		(if (!= overlays -1)
			(DrawPic overlays WIPEDOWN TRUE)
		)
;;;		(if (curRoom controls?)
;;;			((curRoom controls?) draw:)
;;;		)
		; redraw the views that we have saved as addToPics
		(addToPics doit:)

		(theGame setCursor:normalCursor (HaveMouse))

		;Redisplay the status line.
		(StatusLine doit:)

		;Turn sound back on.
		(DoSound RestoreSound)
		(Sound pause:FALSE)

		;The main game loop -- doit:, then wait and doit: again.
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))
		)
	)
	

	(method (init &tmp foo)
		;; Game initialization.  This initializes the generic game system.
		;; The user game module will be responsible for modifying this to
		;; select and start the initial room of the game.

		;Make sure some important modules are loaded in.
		(= foo Motion)
		(= foo Sound)
		(= foo Save)

		;Put the IDs of some important objects in variables for easy (and fast)
		;access.  Init the collections with a null add.
		((= cast theCast) add:)

		((= features theFeatures) add:)					;Pablo
		((= sortedFeatures theSortedFeatures) add:)

		((= sounds theSounds) add:)
		((= regions theRegions) add:)
		((= locales theLocales) add:)
		((= addToPics theAddToPics) add:)
		((= timers theTimers) add:)
		
		;Set the current save/restore directory
		(= curSaveDir (GetSaveDir))

		;Initialize the inventory.
		(Inventory init:)

		;Initialize the user.
		(User init:)
	);game init


	(method (doit)
		;; This is the code which is repeatedly executed in order to
		;; run the game.

		;Check all sounds and timers for completion, which will do any
		;appropriate cue:ing.
		(sounds eachElementDo: #check:)
		(timers eachElementDo: #doit:)

		;Give each character in the cast the chance to do its thing.
		;Show the changes on the screen, then delete any cast members
		;which are scheduled for deletion.
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue:)
		)

		;Execute any script attached to theGame.
		(if script
			(script doit:)
		)

		;Now give each region a chance.
		(regions eachElementDo: #doit:)

		;User now responds to what he sees on screen.
		(User doit:)

		;If somebody wants us to change rooms, they set newRoomNum to do so.
		(if (!= newRoomNum curRoomNum)
			(self newRoom:newRoomNum)
		)

		;Remove any expired timers.
		(timers eachElementDo: #delete:)
		(GameIsRestarting FALSE)
	)


	(method (showSelf)
		(regions showSelf:)
	)


	(method (newRoom n &tmp mX mY theMover theEgo oldCur evt)
		;; Change rooms to room number n.
		

		;Dispose of any PicViews.
		(addToPics dispose:)
		
		;Dispose of non-PicView features left on features list
		(features
			eachElementDo: #dispose:
			release:						;don't want stray nodes, they fragment!
		)
		
		;Dispose the cast, expired timers, non-kept regions, and locales.
		(cast
			eachElementDo: #dispose:,
			eachElementDo: #delete:
		)
		(timers eachElementDo: #delete:)
		(regions
			eachElementDo: #perform: DisposeNonKeptRegion
			release:	;don't want nodes for kept regions, they fragment!
		)
		(locales
			eachElementDo: #dispose:
			release:						;don't want stray nodes, they fragment!
		)

		;Dispose lastCast (internal kernel knowledge of the cast during
		;the previous animation cycle).
		(Animate 0)

		;Do some room number bookkeeping.
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)

		;If resource usage tracking is enabled, flush all non-purgable
		;resources.
		(FlushResources n)

		;Start up the room we're going to.
		(= oldCur (self setCursor: waitCursor TRUE))
		(self
			startRoom: curRoomNum,
			checkAni:,
			setCursor: oldCur (HaveMouse)
		)

		;Set the synonym list.
		(SetSynonyms regions)

		;Eat all mice downs and mice up.
		(while ((= evt (Event new: (| mouseDown mouseUp))) type?)
			(evt dispose:)
		)
		(evt dispose:)
	)


	(method (checkAni &tmp theExtra)
		;; Check animation speed.  If it is not adequate, start converting
		;; members of the cast which are marked as extras (through isExtra:)
		;; in to PicViews until animation speed is okay.

		;Make sure that every thing is drawn on the screen before doing
		;speed tests.
		(Animate (cast elements?) FALSE)
		(Wait 0)

		;Animate the cast then (Wait 0), which returns the length of time
		;since the last animation cycle.  If this exceeds aniThreshold,
		;animation is not deemed adequate and we start converting to PicViews.
		(Animate (cast elements?) FALSE)
		(while (> (Wait 0) aniThreshold)
			(= theExtra (cast firstTrue: #isExtra:))
			(breakif (== theExtra NULL))
			(theExtra addToPic:)
			(Animate (cast elements?) FALSE)
			(cast eachElementDo: #delete:)
		)
	)


	(method (startRoom roomNum)
		;; Initialize a new room.  Regions should be initialized in this
		;; method in the instance of Game, so that the Region is loaded
		;; into the heap below the rooms in the Region.

		;This allows us to break when the heap is as free as it gets with
		;the game running, letting us detect any fragmentation in the heap.
		(if debugOn
			(SetDebug)
		)

		;Initialize the new room and add it to the front of the region list.
		(regions
			addToFront: ((= curRoom (ScriptID roomNum)) init: yourself:)
		)
				
		;Activate Demo Demon if needed, 
		;-----------------------------
;;;		(if demoScripts
;;;			(curRoom setRegions: DEMO)
;;;		)
		
	)


	(method (handleEvent event)
		;; Default event handling for the Game is to pass the event along
		;; to the regions.

		(cond 
			(
				(or
					(regions handleEvent: event)
					(locales handleEvent: event)
				)
			)
			(script (script handleEvent: event))
		)

		(return (event claimed?))
		
	)


	(method (changeScore delta)
		;; Update the game score and reflect the change on the status line
		;; if appropriate.

		(+= score delta)
		(StatusLine doit:)
	)


	(method (restart)
		;;Restart the game.

		(if modelessDialog
			(modelessDialog dispose:)
		)
		(RestartGame)
	)


	(method (save &tmp [comment 20] num oldCur oldPause)
		;; Save the game at its current state.  The user interface work
		;; for this is done in class Save, the actual save in the (SaveGame)
		;; kernel function.

		(Load FONT smallFont)
		(Load CURSOR waitCursor)

		(= oldPause (Sound pause:TRUE))
		(if (PromptForDiskChange TRUE)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(= num (Save doit: @comment))
			(if (!= num -1)
				(= oldCur (self setCursor:waitCursor TRUE))
				(if (not (SaveGame name num @comment version))
					(Print
						GAME 0
						#font: SYSFONT
						#button: {OK} 1
					)
				)
				(self setCursor:oldCur (HaveMouse))
			)
			(PromptForDiskChange FALSE)
		)
		(Sound pause:oldPause)
	)


	(method (restore &tmp [comment 20] num oldCur oldPause)
		;; Restore a previously saved game.  The user interface work
		;; for this is done in class Restore, the actual save in the
		;; (RestoreGame) kernel function.

		(Load FONT smallFont)

		(= oldCur (self setCursor:normalCursor))
		(= oldPause (Sound pause:TRUE))
		(if (PromptForDiskChange TRUE)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(= num (Restore doit: &rest))
			(if (!= num -1)
				(self setCursor:waitCursor TRUE)

				(if (CheckSaveGame name num version)
					;Dump all members of the cast, thus freeing their underbits.
					(cast eachElementDo: #dispose:)
					(cast eachElementDo: #delete:)

					(RestoreGame name num version)
				else
					(Print
						GAME 1
						#font: SYSFONT
						#button: {OK} 1
					)
					(self setCursor:oldCur (HaveMouse))
				)
			)
			(PromptForDiskChange FALSE)
		)
		(Sound pause:oldPause)
	)


	(method (setSpeed newSpeed &tmp oldSpeed)
		;; Set the animation speed for the game, returning the old speed.

		(= oldSpeed speed)
		(= speed newSpeed)
		(return oldSpeed)
	)
	
	
	(method (setCursor form showIt &tmp oldCur)
		;; Set the cursor form, returning the previous form.

		(= oldCur theCursor)
		(= theCursor form)
		(if (== argc 1)
			(SetCursor form)
		else
			(SetCursor form showIt)
		)
		(return oldCur)
	)


	(method (showMem)
		;; Display information about free heap and hunk memory.

		(Printf
			{Free Heap: %u Bytes\n
			Largest ptr: %u Bytes\n
			FreeHunk: %u KBytes\n
			Largest hunk: %u Bytes}

			(MemoryInfo FreeHeap)
			(MemoryInfo LargestPtr)
			(>> (MemoryInfo FreeHunk) 6)
			(MemoryInfo LargestHandle)
		)
	)

	(method (wordFail word &tmp [str 100])
		;; Invoked when the parser can't find a word in the vocabulary.

		(Printf GAME 2 word)
		(return 0)
	)


	(method (syntaxFail)
		;; Invoked when the parser can't parse user input.

		(Print GAME 3)
	)


	(method (semanticFail)
		;; Invoked (not at all, presently) when the parser can parse the
		;; sentence but the sentence doesn't make sense (such as
		;; "give tree to rock").

		(Print GAME 4)
	)


	(method (pragmaFail)
		;; Invoked when a said event remains unclaimed after being sent to
		;; all objects in the game.

		(Print GAME 5)
	)


	(method (notify)
		;; Handle arbitrary communication between Game, Regions, and Rooms.
		;; Protocol and number of parameters are up to the game programmer.
	)


	(method (setScript newScript)
		;; Attach a new script to this object, removing any existing one.
		
		(if script
			(script dispose:)
		)
		(if (= script newScript)
			((= script newScript) init: self &rest)
		)
	)
	(method (cue)
		;; Just cue: any attached script.

		(if script
			(script cue:)
		)
	)
	
)




(class Region kindof Object
	;;; A Region is an area of a game which is larger than a Room and which
	;;; has global actions associated with it.  Music which needs to be played
	;;; across rooms needs to be owned by a Region so that it is not disposed
	;;; on a room change.

	(properties
		name "Rgn"
		script 0			;the ID of a script attached to the Region
		number 0			;the module number of the Region
		timer 0			;the ID of a timer attached to the Region
		keep 0			;0->dispose Region on newRoom:, 1->keep Region on newRoom:
		initialized 0	;has the Region been initialized?
	)

;;;	(methods
;;;		handleEvent		;handle user input
;;;		setScript		;set the script for this Region
;;;		cue				;cue the Region
;;;		newRoom			;invoked when the Game changes rooms
;;;		notify			;communication mechanism between Game, Regions, and Rooms
;;;	)



	(method (init)
		;; Initialize the Region.  Region initialization is controlled by the
		;; 'initialized' property, so that the Region is only initialized
		;; once, upon entry, not each time rooms are changed.

		(if (not initialized)
			(= initialized TRUE)
			(if (not (regions contains: self))
				(regions addToEnd: self)
			)
			(super init:)
		)
	)


	(method (doit)
		;; Default is to check the script.

		(if script
			(script doit:)
		)
	)


	(method (handleEvent event)
		;; Default is to pass the event to any script.

		(if script
			(script handleEvent: event)
		)
		(return (event claimed?))
	)


	(method (dispose)
		;Delete this region from the region list, then dispose any
		;objects attached to/owned by it.
		(regions delete:self)
		(if (IsObject script)
			(script dispose:)
		)
		(if (IsObject timer)
			(timer dispose:)
		)
		(sounds eachElementDo: #clean: self)

		;Remove the Region module from the heap.
		(DisposeScript number)
	)


	(method (setScript newScript)
		;; Attach a new script to this object, removing any existing one.
		
		(if (IsObject script)		(script dispose:))
		(if (= script newScript)
			((= script newScript) init: self &rest)
		)
	)
	

	(method (cue)
		;; Just cue: any attached script.

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
		name "Rm"
		picture 0		;number of picture for this Room
		style -1			;the style in which to draw this Room's picture
		horizon 0		;y coordinate of Room's horizon
		controls 0		;a list of controls (buttons, etc.) in the Room
		north 0			;module number of Room to the north
		east 0			;module number of Room to the east
		south 0			;module number of Room to the south
		west 0			;module number of Room to the west
		curPic 0			;picture number of currently visible picture
		
		picAngle		0	;how far from vertical is our view? 0-89
		vanishingX	160
		vanishingY	-30000
	)

;;;	(methods
;;;		handleEvent		;handle user input
;;;		setRegions		;set the Regions which contain this Room
;;;		setFeatures		;set the Features for this Room
;;;		setLocales		;set the Locales for this Room
;;;		drawPic			;draw the picture for this Room
;;;		overlay			;overlay a picture
;;;	)



	(method (init &tmp how)
		(= number curRoomNum)
		;(= controls roomControls)

		(= perspective picAngle)
		;(= skipFactor 1)

		;Draw a picture (if non zero) in proper style
		(if picture
			(self drawPic:picture)
		)

		;Reposition ego if he hit an edge in the previous room.
		(switch ((User alterEgo?) edgeHit?)
			(NORTH
				((User alterEgo?) y: (- southEdge 1))
			)
			(WEST
				((User alterEgo?) x: (- eastEdge ((User alterEgo?) xStep?)))
			)
			(SOUTH
				((User alterEgo?) y: (+ horizon ((User alterEgo?) yStep?)))
			)
			(EAST
				((User alterEgo?) x: (+ westEdge 1))
			)
		)
		((User alterEgo?) edgeHit:0)
	)


	(method	(doit &tmp nRoom)
		;; Send the doit: to any script, then check to see if ego has
		;; hit the edge of the screen.
		;; - revised by Pablo 11/19/88 to save space

		(if script
			(script doit:)
		)
		(= nRoom
			(switch ((User alterEgo?) edgeHit?)
				(NORTH	north)
				(EAST		east)
				(SOUTH	south)
				(WEST		west)
			)
		)
		(if nRoom (self newRoom:nRoom))
	);doit


;;;	(method (dispose)
;;;		(if controls
;;;			(controls dispose:)
;;;		)
;;;		(super dispose:)
;;;	)


	(method (handleEvent event)
;;;		(or
			(super handleEvent: event)
;;;			(if controls
;;;				(controls handleEvent: event)
;;;			)
;;;		)
		(return (event claimed?))
	)


	(method (setRegions region &tmp i n regID)
		;; Set the regions used by a room.

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


	; attach a locale to the locale list and send it it's init
	(method (setLocales locale &tmp i n locID)
		;; Set the locales used by a room.

		(for	((= i 0))
				(< i argc)
				((++ i))

			(= n [locale i])
			((= locID (ScriptID n))
				number: n
			)
			(locales add: locID)
			(locID init:)
		)
	)


	(method (setFeatures feature &tmp i n featureID)
		;; Set the features used by a room.

		(for	((= i 0))
				(< i argc)
				((++ i))

			(features add: [feature i])
		)
	)


	(method (newRoom n)
		;; Remove this Room from the regions, let the rest of the regions
		;; know about the room change, then put ourselves back in the action.
		(regions
			delete: self,
			eachElementDo: #newRoom: n,
			addToFront: self
		)

		(= newRoomNum n)
		(super newRoom: n)
	)


	(method (drawPic pic theStyle)
		;; Draw the given picture in the appropriate style.

		;; Dispose of addToPics list that is now invalid
		(theAddToPics dispose:)

		(= curPic pic)
		(= overlays -1)
		(DrawPic pic
			(cond
				((== argc 2) theStyle)		;use passed style
				((!= style -1) style)		;use default room style
				(else showStyle)				;use global style
			)
;;;			TRUE									; clear buffer before drawing
;;;			currentPalette						; defaults to 0
		)
	)


	(method (overlay pic theStyle)
		;; Overlay the current picture with another.

		(= overlays pic)
		(DrawPic
			pic
			(cond
				((== argc 2) theStyle)		;use passed style
				((!= style -1) style)		;use default room style
				(else showStyle)				;use global style
			)
			TRUE
;;;			FALSE									; don't clear buffer
;;;			currentPalette						; defaults to 0
		)
	)
)




(class Locale of Object
	;;; A Locale is similar to a Region in that it may encompass many Rooms,
	;;; but its only purpose is to provide default responses to user input.
	;;; Thus, a forest locale will provide generic responses to input like
	;;; 'look forest', 'look tree', 'climb tree', etc.  A locale is attached
	;;; to a Room with the setLocales: method.

	(properties
		number 0				;module number of this Locale
	)

;;;	(methods
;;;		handleEvent			;handle user input
;;;	)


	(method (handleEvent event)
		;; Game programmer must redefine this method.
		(return (event claimed?))
	)


	(method (dispose)
		;Delete this locale from the locale list.
		(locales delete:self)

		;Remove the Locale module from the heap.
		(DisposeScript number)
	)
)




(class StatusLine of Object
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
		name "SL"
		state FALSE		;enabled/disabled
		code 0			;ID of Code to display status line
	)

;;;	(methods
;;;		enable			;display the status line
;;;		disable			;hide the status line
;;;	)


	(method (doit &tmp [theLine 41])
		;; This method calls the application code to format the status
		;; line string at theLine, then draws it.

		(if code
			(code doit: @theLine)
			(DrawStatus (if state @theLine else 0))
		)
	)


	(method (enable)
		;; Display the status line.

		(= state TRUE)
		(self doit:)
	)


	(method (disable)
		;Hide the status line.

		(= state FALSE)
		(self doit:)
	)
)




(procedure (PromptForDiskChange saveDisk
				&tmp ret [saveDevice 40] [curDevice 40] [str 40]
			  )

	;; Used by restore: to prompt the user to change disks if running
	;; on single-drive removable media.

	(= ret TRUE)
	(DeviceInfo GetDevice curSaveDir @saveDevice)
	(DeviceInfo CurDevice @curDevice)
	(if
		(and
			(DeviceInfo SameDevice @saveDevice @curDevice)
			(DeviceInfo DevRemovable @curDevice)
		)

		(Format @str
			GAME 6
			(if saveDisk {SAVE GAME} else {GAME})
			@curDevice
		)

		(= ret
			(if saveDisk
				(Print @str
					#font: SYSFONT
					#button: {OK} TRUE
					#button: {Cancel} FALSE
					#button: {Change Directory} 2
				)
			else
				(Print @str
					#font: SYSFONT
					#button: {OK} TRUE
				)
			)
		)

		(if (== ret 2)
			(= ret (GetDirectory curSaveDir))
		)

	)
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
