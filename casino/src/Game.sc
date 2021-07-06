;;; Sierra Script 1.0 - (do not remove this comment)
(script# GAME)
(include game.sh) (include "994.shm")
(use Main)
(use Intrface)
(use PolyGon)
(use Sound)
(use Save)
(use Motion)
(use Invent)
(use User)
(use System)


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

(instance theSortedFeatures of EventHandler
	(properties
		name "sFeatures"
	)
	
	(method (delete theElement)
		(super delete: theElement)
		(if
			(and
				useSortedFeatures
				(theElement isKindOf: Collection)
				(not (OneOf theElement regions locales))
			)
			(theElement release: dispose:)
		)
	)
)

(class Sounds of EventHandler
	(properties)
	
	(method (pause tOrF)
		(self eachElementDo: #perform mayPause (if argc tOrF else 1))
	)
)

(instance mayPause of Code
	(properties)
	
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

(instance theLocales of EventHandler
	(properties
		name "locales"
	)
)

(instance theAddToPics of EventHandler
	(properties
		name "addToPics"
	)
	
	(method (doit)
		(self eachElementDo: #perform addToObstaclesCode)
		(AddToPic elements)
	)
)

(instance roomControls of Controls
	(properties
		name "controls"
	)
)

(instance theTimers of Set
	(properties
		name "timers"
	)
)

(instance addToObstaclesCode of Code
	(properties
		name "aTOC"
	)
	
	(method (doit thePV &tmp dX dY)
		(if (not (| (thePV signal?) ignrAct))
			(= dX
				(+ (ego xStep?) (/ (CelWide (ego view?) 2 0) 2))
			)
			(= dY (* (ego yStep?) 2))
			(curRoom
				addObstacle:
					((Polygon new:)
						init:
							(- (thePV brLeft?) dX)
							(- (CoordPri 1 (CoordPri (thePV y?))) dY)
							(+ (thePV brRight?) dX)
							(- (CoordPri 1 (CoordPri (thePV y?))) dY)
							(+ (thePV brRight?) dX)
							(+ (thePV y?) dY)
							(- (thePV brLeft?) dX)
							(+ (thePV y?) dY)
						yourself:
					)
			)
		)
	)
)

(class Game of Object
	(properties
		script NULL
		parseLang ENGLISH
		printLang ENGLISH
		subtitleLang NULL
		_detailLevel 3
	)
	
	(method (init)
		Motion
		Sound
		(ScriptID LANGUAGE)
		((= cast theCast) add:)
		((= features theFeatures) add:)
		((= sortedFeatures theSortedFeatures) add:)
		((= sounds Sounds) add:)
		((= regions theRegions) add:)
		((= locales theLocales) add:)
		((= addToPics theAddToPics) add:)
		((= timers theTimers) add:)
		((= theDoits demons) add:)
		(= fastCast NULL)
		(= curSaveDir (GetSaveDir))
		(Inventory init:)
		(if (not user) (= user User))
		(user init:)
	)
	
	(method (doit &tmp event)
		(= gameTime (+ tickOffset (GetTime)))
		(if fastCast
			(while fastCast
				(fastCast eachElementDo: #doit)
				(if
				(and ((= event (Event new:)) type?) fastCast)
					(fastCast firstTrue: #handleEvent event)
				)
				(event dispose:)
				(= gameTime (+ tickOffset (GetTime)))
			)
		)
		(if prints
			(prints eachElementDo: #doit)
			(if (not modelessDialog)
				(if
				(and ((= event (Event new:)) type?) prints)
					(prints firstTrue: #handleEvent event)
				)
				(event dispose:)
				(= gameTime (+ tickOffset (GetTime)))
				(return)
			)
		)
		(sounds eachElementDo: #check)
		(timers eachElementDo: #doit)
		(if modelessDialog (modelessDialog check:))
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue)
		)
		(if script (script doit:))
		(regions eachElementDo: #doit)
		(if (== newRoomNum curRoomNum) (user doit:))
		(theDoits doit:)
		(if (!= newRoomNum curRoomNum)
			(self newRoom: newRoomNum)
		)
		(timers eachElementDo: #delete)
		(GameIsRestarting FALSE)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting)) (GetCWD curSaveDir))
		(self setCursor: waitCursor TRUE init:)
		(self setCursor: normalCursor TRUE)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (replay)
		(if lastEvent (lastEvent dispose:))
		(sortedFeatures release:)
		(if modelessDialog (modelessDialog dispose:))
		(cast eachElementDo: #perform RestoreUpdate)
		(theGame setCursor: waitCursor TRUE)
		(DrawPic (curRoom curPic?) PLAIN TRUE currentPalette)
		(if (!= overlays -1)
			(DrawPic overlays PLAIN FALSE currentPalette)
		)
		(if (curRoom controls?) ((curRoom controls?) draw:))
		(addToPics doit:)
		(theGame setCursor:
			(if (and theIconBar (theIconBar curIcon?))
				((theIconBar curIcon?) cursor?)
			else
				normalCursor
			)
			(HaveMouse)
		)
		(StatusLine doit:)
		(DoSound RestoreSound)
		(Sound pause: FALSE)
		(= tickOffset (- gameTime (GetTime)))
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (newRoom n &tmp mX mY theMover theEgo oldCur evt)
		(addToPics
			eachElementDo: #dispose
			release:
		)
		(features
			eachElementDo: #perform featureDisposeCode
			release:
		)
		(cast
			eachElementDo: #dispose
			eachElementDo: #delete
		)
		(timers eachElementDo: #delete)
		(regions
			eachElementDo: #perform DisposeNonKeptRegion
			release:
		)
		(locales
			eachElementDo: #dispose
			release:
		)
		(theDoits
			release:
		)
		(Animate 0)
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)
		(FlushResources n)
		(self startRoom: curRoomNum checkAni:)
		(Dummy regions)
		(while ((= evt (Event new: (| mouseDown mouseUp))) type?)
			(evt dispose:)
		)
		(evt dispose:)
	)
	
	(method (startRoom roomNum)
		(if debugOn
			(SetDebug)
		)
		(regions addToFront: (= curRoom (ScriptID roomNum)))
		(curRoom init:)
	)
	
	(method (restart)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(RestartGame)
	)
	
	(method (restore &tmp [comment 20] num oldCur oldLang [buf 100] [buf2 5])
		(= oldLang parseLang)
		(= parseLang ENGLISH)
		(Load FONT smallFont)
		(ScriptID SAVE)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(if (PromptForDiskChange TRUE)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= num (Restore doit: &rest)) -1)
				(self setCursor: waitCursor 1)
				(if (CheckSaveGame name num version)
					(RestoreGame name num version)
				else
					(Message MsgGet GAME N_WRONG_INTERP NULL NULL 1 @buf)
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf2)
					(Print @buf
						#font SYSFONT
						#button @buf2 1
					)
					(self setCursor: oldCur (HaveMouse))
					(= parseLang oldLang)
				)
			else
				(= parseLang oldLang)
			)
			(PromptForDiskChange FALSE)
		)
		(Sound pause: FALSE)
	)
	
	(method (save &tmp [comment 20] num oldCur oldLang [buf 100] [buf2 5])
		(= oldLang parseLang)
		(= parseLang ENGLISH)
		(Load FONT smallFont)
		(ScriptID SAVE)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(if (PromptForDiskChange TRUE)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= num (Save doit: @comment)) -1)
				(= parseLang oldLang)
				(= oldCur (self setCursor: waitCursor TRUE))
				(if (not (SaveGame name num @comment version))
					(Message MsgGet GAME N_DISK_FULL NULL NULL 1 @buf)
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf2)
					(Print @buf
						#font SYSFONT
						#button @buf2 1
					)
				)
				(self setCursor: oldCur (HaveMouse))
			)
			(PromptForDiskChange FALSE)
		)
		(Sound pause: FALSE)
		(= parseLang oldLang)
	)
	
	(method (changeScore delta)
		(= score (+ score delta))
		(StatusLine doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) TRUE)
			((and script (script handleEvent: event)) TRUE)
			((& (event type?) userEvent) (self pragmaFail:))
		)
		(event claimed?)
	)
	
	(method (showMem)
		(Printf
			{Free Heap: %u Bytes\nLargest ptr: %u Bytes\nFreeHunk: %u KBytes\nLargest hunk: %u Bytes}
			(MemoryInfo FreeHeap)
			(MemoryInfo LargestPtr)
			(>> (MemoryInfo FreeHunk) 6)
			(MemoryInfo LargestHandle)
		)
	)
	
	(method (setSpeed newSpeed &tmp oldSpeed)
		(= oldSpeed speed)
		(= speed newSpeed)
		(return oldSpeed)
	)
	
	(method (setCursor form showIt theX theY hotX hotY &tmp oldCur)
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
				(if (> argc 4) (SetCursor form 0 0 hotX hotY))
			)
		)
		(return oldCur)
	)
	
	(method (checkAni &tmp theExtra)
		(Animate (cast elements?) FALSE)
		(Wait 0)
		(Animate (cast elements?) FALSE)
		(while (> (Wait 0) aniThreshold)
			(breakif (== (= theExtra (cast firstTrue: #isExtra)) NULL))
			(theExtra addToPic:)
			(Animate (cast elements?) FALSE)
			(cast eachElementDo: #delete)
		)
	)
	
	(method (notify)
	)
	
	(method (setScript newScript)
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
	
	(method (quitGame tOrF)
		(if (or (not argc) tOrF)
			(= quit TRUE)
		)
	)
	
	(method (masterVolume newVol)
		(if argc
			(DoSound MasterVol newVol)
		else
			(DoSound MasterVol)
		)
	)
	
	(method (detailLevel theLevel)
		(if argc
			(= _detailLevel theLevel)
			(cast eachElementDo: #checkDetail)
		)
		(return _detailLevel)
	)
	
	(method (pragmaFail)
	)
)

(class Region of Object
	(properties
		name "Rgn"
		script 0
		number 0
		modNum 0
		noun 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (init)
		(if (not initialized)
			(= initialized TRUE)
			(if (not (regions contains: self))
				(regions addToEnd: self)
			)
			(super init:)
		)
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (dispose)
		(regions delete: self)
		(if (IsObject script) (script dispose:))
		(if (IsObject timer) (timer dispose:))
		(sounds eachElementDo: #clean self)
		(DisposeScript number)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) TRUE)
			(
				(not
					(if
					(and script (or (script handleEvent: event) TRUE))
						(event claimed?)
					)
				)
				(event claimed: (self doVerb: (event message?)))
			)
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb &tmp who)
		(if (not modNum) (= modNum curRoomNum))
		(return
			(if (Message MsgGet modNum noun theVerb NULL 1)
				(messager say: noun theVerb NULL NULL NULL modNum)
			else
				(return FALSE)
			)
		)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(if newScript (newScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
	
	(method (newRoom)
	)
	
	(method (notify)
	)
)

(class Room of Region
	(properties
		name "Rm"
		picture 0
		style -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY -30000
		obstacles 0
		inset 0
	)
	
	(method (init &tmp how)
		(= number curRoomNum)
		(= controls roomControls)
		(= perspective picAngle)
		(if picture (self drawPic: picture))
		(self
			reflectPosn: (user alterEgo?) ((user alterEgo?) edgeHit?)
		)
		((user alterEgo?) edgeHit: 0)
	)
	
	(method (doit &tmp nRoom)
		(if script (script doit:))
		(if
			(= nRoom
				(self edgeToRoom: ((user alterEgo?) edgeHit?))
			)
			(self newRoom: nRoom)
		)
	)
	
	(method (dispose)
		(if controls (controls dispose:))
		(if obstacles (obstacles dispose:))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(and inset (inset handleEvent: event))
					(super handleEvent: event)
				)
			)
			(controls (controls handleEvent: event))
		)
		(event claimed?)
	)
	
	(method (newRoom n)
		(regions
			delete: self
			eachElementDo: #newRoom n
			addToFront: self
		)
		(= newRoomNum n)
		(super newRoom: n)
	)
	
	(method (setRegions region &tmp i n regID)
		(= i 0)
		(while (< i argc)
			(= n [region i])
			((= regID (ScriptID n)) number: n)
			(regions add: regID)
			(if (not (regID initialized?)) (regID init:))
			(++ i)
		)
	)
	
	(method (setFeatures feature &tmp i n featureID)
		(= i 0)
		(while (< i argc)
			(features add: [feature i])
			(++ i)
		)
	)
	
	(method (setLocales locale &tmp i n locID)
		(= i 0)
		(while (< i argc)
			(= n [locale i])
			((= locID (ScriptID n)) number: n)
			(locales add: locID)
			(locID init:)
			(++ i)
		)
	)
	
	(method (drawPic pic theStyle)
		(if addToPics
			(addToPics eachElementDo: #dispose release:)
		)
		(= curPic pic)
		(= overlays -1)
		(DrawPic pic
			(cond 
				((== argc 2) theStyle)
				((!= style -1) style)
				(else showStyle)
			)
			TRUE
			currentPalette
		)
	)
	
	(method (overlay pic theStyle)
		(= overlays pic)
		(DrawPic pic
			(cond 
				((== argc 2) theStyle)
				((!= style -1) style)
				(else showStyle)
			)
			FALSE
			currentPalette
		)
	)
	
	(method (addObstacle obstacle)
		(if (not (IsObject obstacles))
			(= obstacles (List new:))
		)
		(obstacles add: obstacle &rest)
	)
	
	(method (reflectPosn theActor theEdge)
		(switch theEdge
			(NORTH (theActor y: (- southEdge 1)))
			(WEST
				(theActor x: (- eastEdge (theActor xStep?)))
			)
			(SOUTH
				(theActor y: (+ horizon (theActor yStep?)))
			)
			(EAST (theActor x: (+ westEdge 1)))
		)
	)
	
	(method (edgeToRoom edge)
		(switch edge
			(NORTH north)
			(EAST east)
			(SOUTH south)
			(WEST west)
		)
	)
	
	(method (roomToEdge rm)
		(switch rm
			(north NORTH)
			(south SOUTH)
			(east EAST)
			(west WEST)
		)
	)
	
	(method (setInset theInset who reg)
		(if inset
			(inset dispose:)
		)
		(if (and argc theInset)
			(theInset init:
					(if (>= argc 2) who else 0)
					self
					(if (>= argc 3) reg else 0)
			)
		)
	)
)

(class Locale of Object
	(properties
		number 0
	)
	
	(method (dispose)
		(locales delete: self)
		(DisposeScript number)
	)
	
	(method (handleEvent event)
		(event claimed?)
	)
)

(class StatusLine of Object
	(properties
		name "SL"
		state FALSE
		code 0
	)
	
	(method (doit &tmp theLine)
		(if code
			(= theLine (Memory MNeedPtr (if 0 240 else 82)))
			(code doit: theLine)
			(DrawStatus (if state theLine else 0))
			(Memory MDisposePtr theLine)
		)
	)
	
	(method (enable)
		(= state TRUE)
		(self doit:)
	)
	
	(method (disable)
		(= state FALSE)
		(self doit:)
	)
)

(procedure (PromptForDiskChange saveDisk &tmp ret [saveDevice 40] [curDevice 40] str [buf1 40] [buf2 10] [buf3 5])
	(= str (Memory MNeedPtr (if 0 200 else 80)))
	(= ret TRUE)
	(DeviceInfo GetDevice curSaveDir @saveDevice)
	(DeviceInfo CurDevice @curDevice)
	(if
		(and
			(DeviceInfo DevRemovable @curDevice)
			(or
				(DeviceInfo SameDevice @saveDevice @curDevice)
				(not (DeviceInfo SaveDirMounted (theGame name?)))
			)
		)
		(Message MsgGet GAME N_INSERT_DISK NULL NULL 1 @buf1)
		(Message MsgGet GAME N_SAVE_DISK NULL NULL 1 @buf2)
		(Message MsgGet GAME N_GAME_DISK NULL NULL 1 @buf3)
		(Format str
			@buf1
			(if saveDisk @buf2 else @buf3)
			@saveDevice
		)
		(Load FONT userFont)
		(DeviceInfo CloseDevice)
		(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 @buf1)
		(Message MsgGet GAME N_CANCEL_BUTTON NULL NULL 1 @buf2)
		(Message MsgGet GAME N_CHG_DIR_BUTTON NULL NULL 1 @buf3)
		(if
			(==
				(= ret
					(if saveDisk
						(Print str
							#font SYSFONT
							#button @buf1 TRUE
							#button @buf2 FALSE
							#button @buf3 2
						)
					else
						(Print str
							#font SYSFONT
							#button @buf1 TRUE 
						)
					)
				)
				2
			)
			(= ret (GetDirectory curSaveDir))
		)
	)
	(Memory MDisposePtr str)
	(return ret)
)

(instance RestoreUpdate of Code
	(properties
		name "RU"
	)
	
	(method (doit obj &tmp sigBits)
		(if (obj underBits?)
			(= sigBits
				(&
					(= sigBits (| (= sigBits (obj signal?)) stopUpdOn))
					$fffb
				)
			)
			(obj underBits: 0 signal: sigBits)
		)
	)
)

(instance DisposeNonKeptRegion of Code
	(properties
		name "DNKR"
	)
	
	(method (doit region)
		(if (not (region keep?)) (region dispose:))
	)
)

(instance featureDisposeCode of Code
	(properties
		name "fDC"
	)
	
	(method (doit theFeature)
		(if (theFeature respondsTo: #delete)
			(theFeature
				signal: (& (theFeature signal?) (~ viewAdded))
				dispose:
				delete:
			)
		else
			(theFeature dispose:)
		)
	)
)