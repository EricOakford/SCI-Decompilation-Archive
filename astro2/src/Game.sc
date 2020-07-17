;;; Sierra Script 1.0 - (do not remove this comment)
(script# GAME)
(include game.sh)
(use Main)
(use Intrface)
(use Polygon)
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

(instance theFastCast of EventHandler
	(properties
		name "fastCast"
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

(instance theSounds of EventHandler
	(properties
		name "sounds"
	)
	
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
		egoMoveSpeed 0
	)
	
	(method (init)
		Motion
		Sound
		(ScriptID LANGUAGE)
		((= cast theCast) add:)
		((= features theFeatures) add:)
		((= sortedFeatures theSortedFeatures) add:)
		((= sounds theSounds) add:)
		((= regions theRegions) add:)
		((= locales theLocales) add:)
		((= addToPics theAddToPics) add:)
		((= timers theTimers) add:)
		((= theDoits demons) add:)
		((= fastCast theFastCast) add:)
		(= curSaveDir (GetSaveDir))
		(Inventory init:)
		(if (not user) (= user User))
		(user init:)
	)
	
	(method (doit &tmp newEvent)
		(if (fastCast isEmpty:)
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
		else
			(while (not (fastCast isEmpty:))
				(fastCast eachElementDo: #doit)
				(if ((= newEvent (Event new:)) type?)
					(fastCast firstTrue: #handleEvent newEvent)
				)
				(newEvent dispose:)
			)
		)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting)) (GetCWD curSaveDir))
		(self
			setCursor: waitCursor TRUE
			init:
			setCursor: normalCursor (HaveMouse)
		)
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))			
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
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))			
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
		(timers
			eachElementDo: #delete
		)
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
		(SetSynonyms regions)
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
	
	(method (restore &tmp [comment 20] num oldCur oldLang)
		(= oldLang parseLang)
		(= parseLang ENGLISH)
		(Load FONT smallFont)
		(Load CURSOR waitCursor)
		(ScriptID SAVE)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(if (PromptForDiskChange TRUE)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= num (Restore doit: &rest)) -1)
				(self setCursor: waitCursor TRUE)
				(if (CheckSaveGame name num version)
					(RestoreGame name num version)
				else
					(Print GAME 1 #font 0 #button {OK} 1)
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

	(method (save &tmp [comment 20] num oldCur oldLang)
		(= oldLang parseLang)
		(= parseLang ENGLISH)
		(Load FONT smallFont)
		(Load CURSOR waitCursor)
		(ScriptID SAVE)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(if (PromptForDiskChange TRUE)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= num (Save doit: @comment)) -1)
				(= parseLang oldLang)
				(= oldCur (self setCursor: waitCursor TRUE))
				(if (not (SaveGame name num @comment version))
					(Print GAME 0 #font 0 #button {OK} 1)
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
			((== (event type?) userEvent) (self pragmaFail:))
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
	
	(method (setCursor form &tmp oldCur)
		(= oldCur theCursor)
		(= theCursor form)
		(SetCursor form &rest)
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
		timer 0
		keep 0
		initialized 0
		lookStr 0
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
				(event claimed:
					(self doVerb: (event message?)
						(if
							(and inventory theIconBar (== (event message?) verbUse))
							(inventory indexOf: (theIconBar curInvIcon?))
						else
							FALSE
						)
					)
				)
			)
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb)
		(return
			(if (and (== theVerb verbLook) lookStr)
				(Printf GAME 2 lookStr)
				(return TRUE)
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
			((super handleEvent: event))
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
			(EAST (theActor x: 1))
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

(procedure (PromptForDiskChange saveDisk &tmp ret [saveDevice 40] [curDevice 40] str)
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
		(Format str GAME 3
			(if saveDisk {SAVE GAME} else {GAME})
			@saveDevice
		)
		(Load FONT userFont)
		(DeviceInfo CloseDevice)
		(if
			(==
				(= ret
					(if saveDisk
						(Print str
							#font SYSFONT
							#button {OK} TRUE
							#button {Cancel} FALSE
							#button {Change Directory} 2
						)
					else
						(Print str
							#font SYSFONT
							#button {OK} TRUE
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