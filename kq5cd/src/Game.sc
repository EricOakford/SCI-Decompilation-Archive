;;; Sierra Script 1.0 - (do not remove this comment)
(script# 994)
(include sci.sh)
(use Main)
(use Intrface)
(use Polygon)
(use Sound)
(use Save)
(use Motion)
(use Invent)
(use User)
(use System)


(procedure (localproc_0f34 param1 &tmp temp0 [temp1 40] [temp41 40] temp81)
	(= temp81 (Memory 1 (if 0 200 else 80)))
	(= temp0 1)
	(DeviceInfo 0 curSaveDir @temp1)
	(DeviceInfo 1 @temp41)
	(if
		(and
			(DeviceInfo 3 @temp41)
			(or
				(DeviceInfo 2 @temp1 @temp41)
				(not (DeviceInfo 6 (theGame name?)))
			)
		)
		(Format
			temp81
			994
			3
			(if param1 {SAVE GAME} else {GAME})
			@temp1
		)
		(Load rsFONT userFont)
		(DeviceInfo 4)
		(if
			(==
				(= temp0
					(if param1
						(Print
							temp81
							#font
							0
							#button
							{OK}
							1
							#button
							{Cancel}
							0
							#button
							{Change Directory}
							2
						)
					else
						(Print temp81 #font 0 #button {OK} 1)
					)
				)
				2
			)
			(= temp0 (GetDirectory curSaveDir))
		)
	)
	(Memory 3 temp81)
	(return temp0)
)

(instance cast of EventHandler
	(properties)
)

(instance features of EventHandler
	(properties)
)

(instance theDoits of EventHandler
	(properties)
)

(instance sFeatures of EventHandler
	(properties)
	
	(method (delete param1)
		(super delete: param1)
		(if
			(and
				useSortedFeatures
				(param1 isKindOf: Collect)
				(not (OneOf param1 regions locales))
			)
			(param1 release: dispose:)
		)
	)
)

(instance sounds of EventHandler
	(properties)
	
	(method (pause param1)
		(self
			eachElementDo: #perform mayPause (if argc param1 else 1)
		)
	)
)

(instance mayPause of Code
	(properties)
	
	(method (doit param1 param2)
		(if (not (& (param1 flags:) $0001))
			(param1 pause: param2)
		)
	)
)

(instance regions of EventHandler
	(properties)
)

(instance locales of EventHandler
	(properties)
)

(instance addToPics of EventHandler
	(properties)
	
	(method (doit)
		(self eachElementDo: #perform aTOC)
		(AddToPic elements)
	)
)

(instance controls of Controls
	(properties)
)

(instance timers of Set
	(properties)
)

(instance aTOC of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(if (not (| (param1 signal?) $4000))
			(= temp0
				(+ (ego xStep?) (/ (CelWide (ego view?) 2 0) 2))
			)
			(= temp1 (* (ego yStep?) 2))
			(curRoom
				addObstacle:
					((Polygon new:)
						init:
							(- (param1 brLeft?) temp0)
							(- (CoordPri 1 (CoordPri (param1 y?))) temp1)
							(+ (param1 brRight?) temp0)
							(- (CoordPri 1 (CoordPri (param1 y?))) temp1)
							(+ (param1 brRight?) temp0)
							(+ (param1 y?) temp1)
							(- (param1 brLeft?) temp0)
							(+ (param1 y?) temp1)
						yourself:
					)
			)
		)
	)
)

(class Game of Obj
	(properties
		script 0
		parseLang 1
		printLang 1
		subtitleLang 0
		_detailLevel 3
		egoMoveSpeed 0
	)
	
	(method (init)
		Motion
		Sound
		(ScriptID 932)
		((= cast cast) add:)
		((= features features) add:)
		((= sortedFeatures sFeatures) add:)
		((= sounds sounds) add:)
		((= regions regions) add:)
		((= locales locales) add:)
		((= addToPics addToPics) add:)
		((= timers timers) add:)
		((= theDoits theDoits) add:)
		(= fastCast 0)
		(= curSaveDir (GetSaveDir))
		(Inv init:)
		(if (not user) (= user User))
		(user init:)
	)
	
	(method (doit &tmp newEvent)
		(if fastCast
			(while fastCast
				(fastCast eachElementDo: #doit)
				(if
				(and ((= newEvent (Event new:)) type?) fastCast)
					(fastCast firstTrue: #handleEvent newEvent)
				)
				(newEvent dispose:)
			)
		else
			(sounds eachElementDo: #check)
			(timers eachElementDo: #doit)
			(if modelessDialog (modelessDialog check:))
			(Animate (cast elements?) 1)
			(if doMotionCue
				(= doMotionCue 0)
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
			(GameIsRestarting 0)
		)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting)) (GetCWD curSaveDir))
		(self
			setCursor: waitCursor 1
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
		(cast eachElementDo: #perform RU)
		(theGame setCursor: waitCursor 1)
		(DrawPic (curRoom curPic?) 100 TRUE currentPalette)
		(if (!= overlays -1)
			(DrawPic overlays 100 FALSE currentPalette)
		)
		(if (curRoom controls?) ((curRoom controls?) draw:))
		(addToPics doit:)
		(theGame
			setCursor:
				(if (and theIconBar (theIconBar curIcon?))
					((theIconBar curIcon?) cursor?)
				else
					normalCursor
				)
				(HaveMouse)
		)
		(SL doit:)
		(DoSound sndNOP)
		(Sound pause: 0)
		(while (not quit)
			(self doit:)
			(= aniInterval (Wait speed))
		)
	)
	
	(method (newRoom n &tmp [temp0 5] temp5)
		(addToPics eachElementDo: #dispose release:)
		(features eachElementDo: #perform fDC release:)
		(cast eachElementDo: #dispose eachElementDo: #delete)
		(timers eachElementDo: #delete)
		(regions eachElementDo: #perform DNKR release:)
		(locales eachElementDo: #dispose release:)
		(theDoits release:)
		(Animate 0)
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)
		(FlushResources n)
		(self startRoom: curRoomNum checkAni:)
		(SetSynonyms regions)
		(while ((= temp5 (Event new: 3)) type?)
			(temp5 dispose:)
		)
		(temp5 dispose:)
	)
	
	(method (startRoom roomNum)
		(if debugOn (SetDebug))
		(regions addToFront: (= curRoom (ScriptID roomNum)))
		(curRoom init:)
	)
	
	(method (restart)
		(if modelessDialog (modelessDialog dispose:))
		(RestartGame)
	)
	
	(method (restore param1 &tmp [temp0 20] temp20 temp21 theParseLang)
		(= theParseLang parseLang)
		(= parseLang 1)
		(Load rsFONT smallFont)
		(ScriptID 990)
		(= temp21 (theGame setCursor: normalCursor))
		(Sound pause: 1)
		(if (localproc_0f34 1)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= temp20 (Restore doit: &rest)) -1)
				(theGame setCursor: waitCursor 1)
				(if (CheckSaveGame name temp20 version)
					(RestoreGame name temp20 version)
				else
					(Print 994 1 #font 0 #button {OK} 1)
					(theGame setCursor: temp21 (HaveMouse))
					(= parseLang theParseLang)
				)
			else
				(= parseLang theParseLang)
			)
			(localproc_0f34 0)
		)
		(Sound pause: 0)
		(DisposeScript 990)
	)
	
	(method (save &tmp [temp0 20] temp20 temp21 theParseLang)
		(= theParseLang parseLang)
		(= parseLang 1)
		(Load rsFONT smallFont)
		(Load rsCURSOR 456)
		(ScriptID 990)
		(= temp21 (theGame setCursor: normalCursor))
		(Sound pause: 1)
		(if (localproc_0f34 1)
			(if modelessDialog (modelessDialog dispose:))
			(if (!= (= temp20 (Save doit: @temp0)) -1)
				(= parseLang theParseLang)
				(= temp21 (theGame setCursor: waitCursor 1))
				(if (not (SaveGame name temp20 @temp0 version))
					(Print 994 0 #font 0 #button {OK} 1)
				)
				(theGame setCursor: temp21 (HaveMouse))
			)
			(localproc_0f34 0)
		)
		(Sound pause: 0)
		(= parseLang theParseLang)
		(DisposeScript 990)
	)
	
	(method (changeScore delta)
		(= score (+ score delta))
		(SL doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) 1)
			((and script (script handleEvent: event)) 1)
			((== (event type?) 16384) (self pragmaFail:))
		)
		(event claimed?)
	)
	
	(method (showMem)
		(Printf
			{Free Heap: %u Bytes\nLargest ptr: %u Bytes\nFreeHunk: %u KBytes\nLargest hunk: %u Bytes}
			(MemoryInfo 1)
			(MemoryInfo 0)
			(>> (MemoryInfo 3) $0006)
			(MemoryInfo 2)
		)
	)
	
	(method (setSpeed theSpeed &tmp theSpeed_2)
		(= theSpeed_2 speed)
		(= speed theSpeed)
		(return theSpeed_2)
	)
	
	(method (setCursor form &tmp theTheCursor)
		(= theTheCursor theCursor)
		(= theCursor form)
		(SetCursor form &rest)
		(return theTheCursor)
	)
	
	(method (checkAni &tmp temp0)
		(Animate (cast elements?) 0)
		(Wait 0)
		(Animate (cast elements?) 0)
		(while (> (Wait 0) aniThreshold)
			(breakif (== (= temp0 (cast firstTrue: #isExtra)) 0))
			(temp0 addToPic:)
			(Animate (cast elements?) 0)
			(cast eachElementDo: #delete)
		)
	)
	
	(method (notify)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(if newScript (newScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
	
	(method (quitGame param1)
		(if (or (not argc) param1) (= quit 1))
	)
	
	(method (masterVolume param1)
		(if argc
			(DoSound sndINIT param1)
		else
			(DoSound sndINIT)
		)
	)
	
	(method (detailLevel the_detailLevel)
		(if argc
			(= _detailLevel the_detailLevel)
			(cast eachElementDo: #checkDetail)
		)
		(return _detailLevel)
	)
	
	(method (pragmaFail)
	)
)

(class Rgn of Obj
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		lookStr 0
	)
	
	(method (init)
		(if (not initialized)
			(= initialized 1)
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
			((event claimed?) 1)
			(
				(not
					(if
					(and script (or (script handleEvent: event) 1))
						(event claimed?)
					)
				)
				(event
					claimed:
						(self
							doVerb:
								(event message?)
								(if
									(and
										inventory
										theIconBar
										(== (event message?) JOY_DOWNRIGHT)
									)
									(inventory indexOf: (theIconBar curInvIcon?))
								else
									0
								)
						)
				)
			)
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb)
		(return
			(if (and (== theVerb 2) lookStr)
				(Printf 994 2 lookStr)
				(return 1)
			else
				(return 0)
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

(class Rm of Rgn
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		lookStr 0
		picture 0
		style $ffff
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
	
	(method (init &tmp temp0)
		(= number curRoomNum)
		(= controls controls)
		(= perspective picAngle)
		(if picture (self drawPic: picture))
		(self
			reflectPosn: (user alterEgo?) ((user alterEgo?) edgeHit?)
		)
		((user alterEgo?) edgeHit: 0)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
		(if
			(= temp0
				(self edgeToRoom: ((user alterEgo?) edgeHit?))
			)
			(self newRoom: temp0)
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
	
	(method (setRegions region &tmp temp0 theRegion temp2)
		(= temp0 0)
		(while (< temp0 argc)
			(= theRegion [region temp0])
			((= temp2 (ScriptID theRegion)) number: theRegion)
			(regions add: temp2)
			(if (not (temp2 initialized?)) (temp2 init:))
			(++ temp0)
		)
	)
	
	(method (setFeatures feature &tmp temp0 [temp1 2])
		(= temp0 0)
		(while (< temp0 argc)
			(features add: [feature temp0])
			(++ temp0)
		)
	)
	
	(method (setLocales locale &tmp temp0 theLocale temp2)
		(= temp0 0)
		(while (< temp0 argc)
			(= theLocale [locale temp0])
			((= temp2 (ScriptID theLocale)) number: theLocale)
			(locales add: temp2)
			(temp2 init:)
			(++ temp0)
		)
	)
	
	(method (drawPic pic theStyle)
		(if addToPics
			(addToPics eachElementDo: #dispose release:)
		)
		(= curPic pic)
		(= overlays -1)
		(DrawPic
			pic
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
		(DrawPic
			pic
			(cond 
				((== argc 2) theStyle)
				((!= style -1) style)
				(else showStyle)
			)
			FALSE
			currentPalette
		)
	)
	
	(method (addObstacle param1)
		(if (not (IsObject obstacles))
			(= obstacles (List new:))
		)
		(obstacles add: param1 &rest)
	)
	
	(method (reflectPosn param1 param2)
		(switch param2
			(1 (param1 y: 188))
			(4
				(param1 x: (- 319 (param1 xStep?)))
			)
			(3
				(param1 y: (+ horizon (param1 yStep?)))
			)
			(2 (param1 x: 1))
		)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(1 north)
			(2 east)
			(3 south)
			(4 west)
		)
	)
	
	(method (roomToEdge param1)
		(switch param1
			(north 1)
			(south 3)
			(east 2)
			(west 4)
		)
	)
)

(class Locale of Obj
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

(class SL of Obj
	(properties
		state $0000
		code 0
	)
	
	(method (doit &tmp temp0)
		(if code
			(= temp0 (Memory 1 (if 0 240 else 82)))
			(code doit: temp0)
			(DrawStatus (if state temp0 else 0))
			(Memory 3 temp0)
		)
	)
	
	(method (enable)
		(= state 1)
		(self doit:)
	)
	
	(method (disable)
		(= state 0)
		(self doit:)
	)
)

(instance RU of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (param1 underBits?)
			(= temp0
				(&
					(= temp0 (| (= temp0 (param1 signal?)) $0001))
					$fffb
				)
			)
			(param1 underBits: 0 signal: temp0)
		)
	)
)

(instance DNKR of Code
	(properties)
	
	(method (doit param1)
		(if (not (param1 keep?)) (param1 dispose:))
	)
)

(instance fDC of Code
	(properties)
	
	(method (doit param1)
		(if (param1 respondsTo: #delete)
			(param1
				signal: (& (param1 signal?) $ffdf)
				dispose:
				delete:
			)
		else
			(param1 dispose:)
		)
	)
)
