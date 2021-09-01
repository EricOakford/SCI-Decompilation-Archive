;;; Sierra Script 1.0 - (do not remove this comment)
(script# rWizardGame)
(include game.sh) (include maze.sh)
(use Main)
(use StatusBar)
(use wizGameMazeBug)
(use wizGameMoveRock)
(use wizGameIsNear)
(use Intrface)
(use KeyCursor)
(use LoadMany)
(use Cat)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	wizGame 0
	objList 1
	WizGameTryCastSpell 2
	openSign 3
	fetchSign 4
	trigSign 5
	playerBug 6
	flame 7
	flameSign 8
	WizGameCastSpell 9
	WizGameGetRock 10
	proc32_11 11
	proc32_12 12
	proc32_13 13
)

(local
	local0
	local1
	theSeconds =  4
	currentTime_2
	spellCast
	theFetchable
	local6
	local7
	local8
	local9
	egoMagicSkill
	[rockObjs 8]
	[bridgeObjs 17]
	[ladderObjs 7]
	rockInfo = [36 58 0 282 60 0 203 88 0 133 124 0 100 156 0 254 141 0 153 182 0 252 168]
	bridgeInfo = [74 43 2 140 42 3 234 42 3 19 73 1 53 89 5 114 78 2 158 83 4 232 64 3 301 74 1 266 91 1 120 127 2 182 137 1 240 172 3 278 174 4 138 10 0 157 10 0 176 10]
	ladderInfo = [84 75 1 43 117 1 181 120 1 285 116 1 199 163 1 118 10 0 196 10]
	local139 = [0 35 105 47 30 59 105 35 235 47 193 50 235 35 319 47 278 59 0 47 56 110 54 131 186 74 235 98 230 101 56 47 227 110 154 111 227 47 319 110 293 134 0 110 80 132 172 182 80 110 166 132 55 158 166 110 245 124 182 130 245 110 319 150 199 150 0 155 85 189 199 150 168 125 220 189 320 200]
)
(procedure (WizGameTryCastSpell spell)
	(return
		(if (CastSpell spell)
			(WizGameCastSpell spell)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (WizGameCastSpell theSpellCast)
	(if spellCast (proc32_11))
	(= spellCast theSpellCast)
	(= currentTime_2 (GetTime SYSTIME1))
	(= wizGameSpellTime (+ 2 (/ [egoStats theSpellCast] 10)))
	(timeBar value: wizGameSpellTime draw:)
)

(procedure (WizGameGetRock param1 theRockObjs_2 &tmp theRockObjs)
	(= theRockObjs [rockObjs param1])
	(if (< 1 argc) (= [rockObjs param1] theRockObjs_2))
	(return theRockObjs)
)

(procedure (proc32_11 &tmp [temp0 2])
	(= wizGameSpellTime 0)
	(timeBar value: wizGameSpellTime draw:)
	(switch spellCast
		(FLAMEDART
			(flame posn: 500 500 setCycle: 0 stopUpd:)
		)
		(FETCH
			(if theFetchable
				(theFetchable drop: (theFetchable x?) (theFetchable y?))
				(= theFetchable 0)
			)
			(theGame setCursor: normalCursor 1)
		)
	)
	(= spellCast 0)
)

(procedure (proc32_12 param1 param2 &tmp temp0 theRockObjs)
	(objList empty:)
	(= temp0 param1)
	(while (< temp0 param2)
		(if (= theRockObjs [rockObjs temp0])
			(objList
				add:
					((CursorCoords new:)
						cursorX: (theRockObjs x?)
						cursorY: (theRockObjs y?)
					)
			)
		)
		(++ temp0)
	)
)

(procedure (proc32_13 param1 param2 &tmp temp0)
	(objList empty:)
	(= temp0 param1)
	(while (< temp0 param2)
		(if (not [rockObjs temp0])
			(objList
				add:
					((CursorCoords new:)
						cursorX: [rockObjs (+ 32 temp0 temp0 temp0)]
						cursorY: [rockObjs (+ 33 temp0 temp0 temp0)]
					)
			)
		)
		(++ temp0)
	)
)

(procedure (localproc_003e param1 param2 param3 param4 param5 param6)
	(return
		(if
			(and
				(<= param3 param1)
				(< param1 param5)
				(<= param4 param2)
			)
			(< param2 param6)
		else
			0
		)
	)
)

(procedure (localproc_005c param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2)
	(= local7 (= local9 1000))
	(= local6 (= local8 param3))
	(= temp0 param3)
	(= temp1 (+ param3 param3 param3))
	(while (< temp0 param4)
		(cond 
			(
			(and param5 (< [rockInfo (+ temp1 1)] (- param2 5))))
			(
				(<
					(= temp2
						(GetDistance
							param1
							param2
							[rockInfo temp1]
							[rockInfo (+ temp1 1)]
						)
					)
					local7
				)
				(if (< local7 local9)
					(= local9 local7)
					(= local8 local6)
				)
				(= local7 temp2)
				(= local6 temp0)
			)
		)
		(++ temp0)
		(= temp1 (+ temp1 3))
	)
	(return local6)
)

(procedure (localproc_137c param1 param2 param3 &tmp temp0 temp1)
	(-- param3)
	(repeat
		(if [rockObjs (= temp0 (Random param2 param3))] (break))
	)
	(= [rockObjs param1] [rockObjs temp0])
	([rockObjs temp0] listIndex: param1)
	(= [rockObjs temp0] 0)
	(= temp1 (+ param1 param1 param1))
	([rockObjs param1]
		setCel: [rockInfo (+ temp1 2)]
		setPri: 1
		posn: [rockInfo temp1] [rockInfo (+ temp1 1)]
	)
)

(class Rock of View
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view NULL
		loop NULL
		cel NULL
		priority 0
		underBits 0
		signal $0101
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		listIndex 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== spellCast OPEN)
				(or
					(MousedOn self event 0)
					(and
						(== (event message?) ENTER)
						(== (event type?) keyDown)
						(== x (event x?))
						(== y (event y?))
					)
				)
			)
			(event claimed: TRUE)
			(= wizGameSpellTime 0)
			(WizGameMoveRock listIndex)
			(proc32_11)
			(= magesMazeCommand mmazeCHOOSE)
			(wizGame setScript: 0)
		)
	)
)

(instance lasso of Cat
	(properties
		y 300
		x 400
		view rWizardGame
		loop 8
		illegalBits $0000
		doCast 1
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== spellCast FETCH)
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event type: 2)
		)
		(super handleEvent: event)
	)
	
	(method (posn)
		(super posn: &rest)
		(if theFetchable (theFetchable posn: &rest))
	)
)

(class Fetchable of View
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view NULL
		loop NULL
		cel NULL
		priority 0
		underBits 0
		signal $0101
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		listIndex 0
		fetchType 0
		inMotion 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not inMotion)
				(== spellCast FETCH)
				(or
					(MousedOn self event 0)
					(and
						(== (event message?) ENTER)
						(== (event type?) keyDown)
						(== x (event x?))
						(== y (event y?))
					)
				)
			)
			(event claimed: TRUE)
			(self pickUp: event)
		)
	)
	
	(method (pickUp param1)
		(if (not inMotion)
			(if (== fetchType (= inMotion 1))
				(= [rockObjs listIndex] 0)
				(= local1 1)
				(proc32_13 25 32)
			else
				(= [rockObjs listIndex] 0)
				(= local0 1)
				(proc32_13 8 25)
			)
			(= theFetchable self)
			(self setCel: 0 setPri: 10 startUpd:)
			(lasso
				setLoop: 8
				setPri: 11
				ignoreActors: 1
				init:
				posn: x y z
				setCycle: Forward
				caller: self
				track: param1
			)
		)
	)
	
	(method (drop param1 param2 &tmp temp0 temp1 temp2)
		(= temp0 (OnControl CMAP param1 param2))
		(switch fetchType
			(1
				(= temp1 (localproc_005c param1 param2 25 32 0))
			)
			(2
				(= temp1 (localproc_005c param1 param2 8 25 0))
			)
		)
		(= inMotion (= theFetchable (= spellCast 0)))
		(lasso dispose:)
		(theGame setCursor: normalCursor 1)
		(if (== temp1 -1)
			(self dispose:)
		else
			(= [rockObjs temp1] self)
			(self listIndex: temp1)
			(= temp2 (+ temp1 temp1 temp1))
			(self
				setCel: [rockInfo (+ temp2 2)]
				setPri: 1
				posn: [rockInfo temp2] [rockInfo (+ temp2 1)]
				stopUpd:
			)
		)
		(= local0 0)
		(= local1 0)
		(objList empty:)
		(proc32_11)
		(= magesMazeCommand mmazeCHOOSE)
		(wizGame setScript: 0)
	)
	
	(method (cue)
		(self drop: x y)
	)
)

(class SpecialBug of MazeBug
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view NULL
		loop NULL
		cel NULL
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits cWHITE
		xLast 0
		yLast 0
		xStep 2
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		startX 0
		startY 0
		deadTime 0
		otherBug 0
		form 0
		smarts 5
		path 0
		onALadder 0
	)
	
	(method (doit)
		(super doit:)
		(if (or (>= x 320) (>= y 190))
			(mover completed: 1)
			(wizGame cue:)
		else
			(self chooseRoute: 0)
		)
	)
	
	(method (cue)
		(= onALadder FALSE)
		(self chooseRoute: 1)
	)
	
	(method (nearRock param1)
		(proc242_0 self 3 param1)
	)
	
	(method (nearBridge param1)
		(proc242_0 self 2 param1)
	)
	
	(method (nearLadder param1)
		(proc242_0 self 1 param1)
	)
	
	(method (chooseRoute param1 &tmp theSpecialBugPath specialBugPath theRockInfo theRockInfo_2 temp4 temp5)
		(if
			(and
				wizGameSpellTime
				(== spellCast FLAMEDART)
				(< (self distanceTo: flame) 120)
			)
			(if
				(or
					(not mover)
					(!= (mover x?) (flame x?))
					(!= (mover y?) (flame y?))
				)
				(self setMotion: MazeMove (flame x?) (flame y?))
			)
			(return)
		)
		(if (== form 1)
			(if (and mover onALadder) (return))
			(= theRockInfo
				[rockInfo (= temp5
					(+ (= temp4 (localproc_005c x y 25 32 1)) temp4 temp4)
				)]
			)
			(if
				(and
					(> (= theRockInfo_2 [rockInfo (+ temp5 1)]) (- y 6))
					(<= (GetDistance x y theRockInfo theRockInfo_2) 40)
					(or
						(not mover)
						(!= (mover x?) theRockInfo)
						(!= (mover y?) (+ theRockInfo_2 10))
					)
				)
				(self
					path: 0
					onALadder: 1
					setMotion: MazeMove theRockInfo (+ theRockInfo_2 10) self
				)
				(return)
			)
		)
		(if
			(and
				(not param1)
				mover
				(self
					inRect:
						[local139 path]
						[local139 (+ path 1)]
						[local139 (+ path 2)]
						[local139 (+ path 3)]
				)
			)
			(return)
		)
		(= specialBugPath (self path?))
		(= theSpecialBugPath 0)
		(while (< theSpecialBugPath 78)
			(if
				(localproc_003e
					x
					y
					[local139 theSpecialBugPath]
					[local139 (+ theSpecialBugPath 1)]
					[local139 (+ theSpecialBugPath 2)]
					[local139 (+ theSpecialBugPath 3)]
				)
				(break)
			)
			(= theSpecialBugPath (+ theSpecialBugPath 6))
		)
		(if (== theSpecialBugPath 78)
			(if mover
				(return)
			else
				(= theSpecialBugPath specialBugPath)
			)
		)
		(self
			path: theSpecialBugPath
			setMotion:
				MazeMove
				[local139 (+ theSpecialBugPath 4)]
				[local139 (+ theSpecialBugPath 5)]
				self
		)
	)
)

(instance playerBug of SpecialBug
	(properties
		view vWizGameMonster
		startX 60
		startY 46
		smarts 3
	)
)

(instance wizBug of SpecialBug
	(properties
		view vWizGameMonster
		loop 3
		startX 303
		startY 40
		smarts 8
	)
	
	(method (changeForm)
		(if (or (!= form 1) (!= (self onControl: origin) cGREEN))
			(super changeForm:)
		)
	)
)

(instance gameSound of Sound
	(properties
		number 79
		priority 2
		loop -1
	)
)

(instance flame of Prop
	(properties
		y 500
		x 500
		view rWizardGame
		loop 6
	)
)

(instance fetchSign of Prop
	(properties
		y 28
		x 48
		view rWizardGame
		priority 5
	)
)

(instance openSign of Prop
	(properties
		y 28
		x 122
		view rWizardGame
		loop 1
		priority 5
	)
)

(instance trigSign of Prop
	(properties
		y 28
		x 198
		view rWizardGame
		loop 2
		priority 5
	)
)

(instance flameSign of Prop
	(properties
		y 28
		x 274
		view rWizardGame
		loop 7
		priority 5
	)
)

(instance pointBar of StatusBar
	(properties
		x 26
		y 12
		titleCel 2
	)
)

(instance timeBar of StatusBar
	(properties
		max 10
		x 253
		y 12
		titleCel 5
	)
)

(instance wizGame of Room
	(properties
		picture 32
		style $0006
	)
	
	(method (init &tmp temp0 temp1 temp2 theRockInfo theRockInfo_2)
		(LoadMany SCRIPT MAZEBUG 205 KEYCURSOR 239 240 241 242)
		(LoadMany VIEW rWizardGame vWizGameMonster statusBarView)
		(super init: &rest)
		(theGame setCursor: normalCursor TRUE)
		(++ magesMazePlayCount)
		(SolvePuzzle f32PlayMaze 5 MAGIC_USER)
		(= temp0 0)
		(while (< temp0 4)
			(repeat
				(if (not [rockObjs (= temp2 (Random 0 7))]) (break))
			)
			(= theRockInfo
				[rockInfo (= temp1 (+ temp2 temp2 temp2))]
			)
			(= theRockInfo_2 [rockInfo (+ temp1 1)])
			((= [rockObjs temp2] (Rock new:))
				listIndex: temp2
				view: rWizardGame
				setLoop: 3
				setCel: 0
				setPri: 4
				x: theRockInfo
				y: theRockInfo_2
				ignoreActors: 1
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 5)
			(repeat
				(if (not [bridgeObjs (= temp2 (Random 0 13))]) (break))
			)
			(= temp1 (+ temp2 temp2 temp2))
			((= [bridgeObjs temp2] (Fetchable new:))
				listIndex: (+ temp2 8)
				fetchType: 2
				view: rWizardGame
				setLoop: 4
				setCel: [bridgeInfo (+ temp1 2)]
				setPri: 1
				ignoreActors: 1
				posn: [bridgeInfo temp1] [bridgeInfo (+ temp1 1)]
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 2)
			(repeat
				(if (not [ladderObjs (= temp2 (Random 0 4))]) (break))
			)
			(= temp1 (+ temp2 temp2 temp2))
			((= [ladderObjs temp2] (Fetchable new:))
				listIndex: (+ temp2 25)
				fetchType: 1
				view: rWizardGame
				setLoop: 5
				setCel: [ladderInfo (+ temp1 2)]
				setPri: 1
				ignoreActors: 1
				posn: [ladderInfo temp1] [ladderInfo (+ temp1 1)]
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(Bclr fStopMaze)
		(fetchSign init: stopUpd:)
		(openSign init: stopUpd:)
		(trigSign init: stopUpd:)
		(flameSign init: stopUpd:)
		(pointBar max: (MaxMana) value: [egoStats MANA] init:)
		(= wizGameSpellTime 0)
		(timeBar init:)
		(playerBug
			setLoop: 0
			setCycle: Forward
			posn: 60 46
			lowPri:
			smarts: (/ (+ [egoStats INT] 5) 10)
			otherBug: wizBug
			path: 0
			init:
			setMotion: MazeMove [local139 4] [local139 5] playerBug
		)
		(wizBug
			setLoop: 3
			setCycle: Forward
			posn: 303 40
			lowPri:
			otherBug: playerBug
			path: 12
			init:
			setMotion: MazeMove [local139 16] [local139 17] wizBug
			setScript: wizScript
		)
		(flame setPri: 15 init: setCycle: Forward stopUpd:)
		(gameSound init: play:)
		(directionHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(objList init: add:)
		(= magesMazeCommand mmazeCHOOSE)
		(Joystick 12 30)
	)
	
	(method (doit &tmp theCurrentTime_2 theMagesMazeCommand)
		(if (!= egoMagicSkill [egoStats MANA])
			(= egoMagicSkill [egoStats MANA])
			(pointBar value: egoMagicSkill draw:)
		)
		(if
			(and
				wizGameSpellTime
				(!= (= theCurrentTime_2 (GetTime SYSTIME1)) currentTime_2)
			)
			(= currentTime_2 theCurrentTime_2)
			(if (-- wizGameSpellTime)
				(timeBar value: wizGameSpellTime draw:)
			else
				(proc32_11)
				(= magesMazeCommand mmazeCHOOSE)
				(wizGame setScript: 0)
			)
		)
		(if magesMazeCommand
			(= theMagesMazeCommand magesMazeCommand)
			(= magesMazeCommand mmazeNOTHING)
			(switch theMagesMazeCommand
				(1
					(self setScript: (ScriptID 239 0))
				)
				(2
					(self setScript: (ScriptID 240 0) 0 24)
				)
				(3
					(self setScript: (ScriptID 240 0) 0 17)
				)
				(4
					(self setScript: (ScriptID 240 0) 0 19)
				)
				(5
					(self setScript: (ScriptID 240 1))
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(timeBar dispose:)
		(pointBar dispose:)
		(objList dispose:)
		(if (HaveMouse)
			(theGame setCursor: normalCursor TRUE)
		else
			(theGame setCursor: normalCursor TRUE 304 174)
		)
		(Joystick JoyRepeat 0)
		(super dispose:)
		(LoadMany FALSE MAZEBUG 205 KEYCURSOR 239 240 241 242)
	)
	
	(method (cue &tmp temp0 temp1)
		(cond 
			((Btst fStopMaze)
				(Print 32 0)
				; "Too much for you?  I guess you should work on your magic.  Maybe next time you'll be more of a challenge."
			)
			(((wizBug mover?) completed?)
				(Print 32 1)
				; "Guess I still have a knack for the old Maze game."
			)
			((not (ego knows: DAZZLE))
				(if
					(<
						(= temp0 (+ magesMazePlayCount magesMazePlayCount))
						500
					)
					(= temp0 500)
				)
				(= temp1 (if (== magesMazePlayCount 1) {} else {s}))
				(Printf 32 2 magesMazePlayCount temp1 temp0)
				(Print 32 3)
				(ego learn: DAZZLE 10)
				(SolvePuzzle f32WinMaze 12 MAGIC_USER)
				; "As I promised, I shall now teach you the spell known as `Erasmus' Razzle Dazzle'."
			)
			(else
				(Print 32 4)
				; "Well!  You don't have to rub it in, you know!"
			)
		)
		(curRoom newRoom: 31)
	)
)

(instance wizScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 clientX clientY clientForm temp5 temp6 temp7)
		(switch (= state newState)
			(0 (= seconds theSeconds))
			(1
				(= clientX (client x?))
				(= clientY (client y?))
				(= clientForm (client form?))
				(= temp1 (localproc_005c clientX clientY 0 32 1))
				(= temp6 local7)
				(= temp7 1)
				(while (< temp7 3)
					(= temp5 (+ temp1 temp1 temp1))
					(++ temp7)
					(cond 
						(
							(and
								(< (client distanceTo: playerBug) 25)
								(<= -1 (- clientForm (playerBug form?)))
								(<= (- clientForm (playerBug form?)) 0)
							)
							(client changeForm:)
						)
						(
						(and (!= clientForm 0) (== (client onControl: origin) cLGREEN)) (client changeForm:))
						((> temp6 50))
						((and (< 0 temp1) (< temp1 8) [rockObjs temp1])
							(WizGameMoveRock temp1)
							(if (== spellCast 17) (proc32_12 0 8))
						)
						(
						(and (< 8 temp1) (< temp1 25) (not [rockObjs temp1]))
							(localproc_137c temp1 8 25)
							(if (== spellCast 24)
								(if local0 (proc32_13 8 25) else (proc32_12 8 25))
							)
						)
						((and (< 25 temp1) (< temp1 32))
							(cond 
								((not [rockObjs temp1])
									(localproc_137c temp1 25 32)
									(if (== spellCast 24)
										(if local1 (proc32_13 25 32) else (proc32_12 25 32))
									)
								)
								((!= clientForm 1) (client changeForm:))
								(else (-- temp7))
							)
						)
						(else (-- temp7))
					)
					(self changeState: 0)
					(++ temp7)
					(= temp6 local9)
					(= temp1 local8)
				)
			)
		)
	)
)

(instance objList of InputList
	(properties)
)
