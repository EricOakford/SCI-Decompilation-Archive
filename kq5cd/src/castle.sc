;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use PChase)
(use PolyPath)
(use Polygon)
(use Block)
(use Reverse)
(use Avoider)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	castle 0
	theAura 1
	theCat 2
	theHenchMan 3
	theMagicDoor 4
	theRings 5
	theFish 6
	theWizard 7
	thePeas 8
	theHenchManScript 9
	theThrowPeasScript 10
	theCatScript 11
	theWizardScript 12
	theThrowFishScript 13
	theCatRunScript 14
	theBagCatScript 15
	InitCat 16
	CastleHandsOff 17
	CastleHandsOn 18
	CastleEgoSpeed 19
)

(local
	local0
	local1
	local2
	theCatX
	theCatY
	local5
	local6
	local7
	thisTime
	henchmanRectLeft
	henchmanRectTop
	henchmanRectRight
	henchmanRectBottom
	local13
	local14
	local15 = [1002 116 63 4 11 29 20 28 29]
	local24 = [1003 10 10 4 11 25 23 31 31]
	local33 = [1003 160 20 4 11 25 23 31 31]
	local42 = [1038 160 20 2 11 22 31 16 37]
	local51 = [1038 10 10 2 11 22 31 16 37]
	local60 = [0 5801 1 7016 0 5803]
)
(procedure (InitCat)
	(if (and (!= catState 7) (> (Random 0 100) 20))
		(Load SOUND 835)
		(switch curRoomNum
			(57
				(theCat posn: 91 172 init:)
			)
			(58
				(theCat posn: 103 115 init:)
			)
			(59
				(theCat posn: 209 162 init:)
			)
			(60
				(theCat posn: 88 149 init:)
			)
			(61
				(theCat posn: 138 144 init:)
			)
			(63
				(theCat posn: 88 169 init:)
			)
			(64
				(theCat posn: 170 173 init:)
			)
		)
		(= catRoom curRoomNum)
		(= catState 1)
	)
)

(procedure (CastleHandsOff)
	(HandsOff)
)

(procedure (CastleHandsOn)
	(HandsOn)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		moveSpeed: (theGame egoMoveSpeed?)
		setCycle: KQ5SyncWalk
		setStep: 3 2
		normal: 1
		illegalBits: cWHITE
		ignoreActors: FALSE
	)
)

(procedure (CastleEgoSpeed)
	(cond 
		((< (theGame egoMoveSpeed?) 3)
			(ego cycleSpeed: 2)
		)
		((< (theGame egoMoveSpeed?) 6)
			(ego cycleSpeed: 4)
		)
		(else
			(ego cycleSpeed: 6)
		)
	)
)

(class castle of Region
	
	(method (init)
		(super init: &rest)
		(if (and (== catState 7) (== catRoom curRoomNum))
			(theCat init: ignoreActors: FALSE setScript: catInBag)
		)
		(if (== curRoomNum 58)
			(Load VIEW 898)
		else
			(Load VIEW 884)
		)
		(if
			(and
				(< henchmanState 4)
				(!= (theMusic number?) 836)
				(not (OneOf curRoomNum 54 67 96 57))
			)
			(theMusic number: 836 loop: -1 playBed:)
		)
		(Bclr fCastleTimeStopped)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (not (Btst fCastleTimeStopped)) (!= thisTime (GetTime SYSTIME1)))
			(= thisTime (GetTime SYSTIME1))
			(= local5 1)
		)
		(if local5
			(= local5 0)
			(if (> wizardTimer 1)
				(if (== (-- wizardTimer) 2)
					(switch curRoomNum
						(58
							(Load VIEW 701)
							(Load VIEW 704)
						)
						(65
							(Load VIEW 702)
							(Load VIEW 127)
						)
						(else 
							(Load VIEW 705)
							(Load VIEW 132)
						)
					)
				)
				(if (== wizardTimer 1)
					(= wizardState 3)
					(Bset fCastleTimeStopped)
				)
			)
			(if (and henchmanTimer (not (-- henchmanTimer)))
				(if (not henchmanState)
					(if (== curRoomNum 63)
						(= wizardState 6)
					else
						(= wizardTimer 3)
					)
				else
					(= henchmanTimer 5)
				)
			)
			(if (and local6 (not (-- local6)))
				(CastleHandsOn)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript RANDCYC)
		(DisposeScript REVERSE)
		(DisposeScript CHASE)
		(DisposeScript JUMP)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(= initialized FALSE)
		(theCat setScript: 0 dispose:)
		(theHenchMan setScript: 0 dispose:)
		(theWizard setScript: 0 dispose:)
		(if (and (!= catState 7) (== catState 1))
			(= henchmanState 0)
			(= catState 6)
			(= wizardState 2)
			(if henchmanTimer
				(= henchmanTimer 3)
			else
				(= wizardTimer 10)
			)
		)
		(super newRoom: n &rest)
	)
)

(instance theMagicDoor of Prop
	(properties
		view 695
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance theAura of Prop
	(properties
		view 703
		priority 15
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance theRings of Prop
	(properties
		view 697
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance theHenchMan of Actor
	(properties
		x 1000
		y 1000
		signal (| ignrAct ignrHrz)
		xStep 4
	)
	
	(method (init)
		(super init:)
		(self
			view: (if (== curRoomNum 58) 898 else 884)
			setCycle: Walk
			setLoop: -1
			looper: 0
			setScript: theHenchManScript
		)
		(if (not henchmanState)
			(= henchmanState 1)
		)
		(if (!= catState 7)
			(= catRoom 0)
		)
		(if (and (== curRoomNum 59) (== henchmanState 8))
			(henchmanPoly points: @henchmanPts size: 4)
			(curRoom addObstacle: henchmanPoly)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(switch henchmanState
						(3 (SpeakAudio 32))
						(7 (SpeakAudio 33))
						(8 (SpeakAudio 33))
					)
					(event claimed: TRUE)
				)
				(verbDo
					(if (and (!= henchmanState 7) (!= henchmanState 8))
						(SpeakAudio 42)
					else
						(SpeakAudio 43)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iPeas
							(cond 
								((== henchmanState 4)
									(event claimed: FALSE)
								)
								((== ((inventory at: iPeas) cel?) 4)
									(CastleHandsOff)
									(= local6 2)
									(SpeakAudio 47)
									(CastleHandsOn)
									(event claimed: TRUE)
								)
								((and (< (ego distanceTo: self) 70) (!= henchmanState 7))
									(CastleHandsOff)
									(= local6 2)
									(SpeakAudio 48)
									(event claimed: TRUE)
								)
								(else
									(curRoom setScript: theThrowPeasScript)
									(event claimed: TRUE)
								)
							)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 49)
							(event claimed: TRUE)
						)
					)
				)
				(verbTalk
					(SpeakAudio 52)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance thePeas of Actor
	(properties
		view 909
		signal (| ignrAct ignrHrz)
	)
)

(instance theThrowPeasScript of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(if (!= henchmanState 3)
					(self init:)
				else
					(self cue:)
				)
			)
			(2
				(ego put: iPeas)
				(CastleHandsOff)
				(ego setMotion: 0)
				(SolvePuzzle 3)
				(Bset fThrewPeas)
				((inventory at: iPeas)
					cel: (+ 1 ((inventory at: 24) cel?))
					cursor: theEmptyBagCursor
					yourself:
				)
				(ego put: iPeas curRoomNum)
				(ego get: iPeas)
				(theHenchMan setScript: 0)
				((ego head?) hide:)
				(ego
					view: (if (== curRoomNum 58) 907 else 909)
					normal: 0
					setCel: 0
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: (if (< (ego x?) (theHenchMan x?)) 0 else 1)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(if (< (ego x?) (theHenchMan x?))
					(thePeas
						init:
						show:
						setCel: 0
						setLoop: 2
						posn: (+ (ego x?) 21) (- (ego y?) 18)
					)
				else
					(thePeas
						init:
						show:
						setCel: 0
						setLoop: 3
						posn: (- (ego x?) 18) (- (ego y?) 20)
					)
				)
				(= cycles 2)
			)
			(4
				(theMusic number: 136 loop: 1 playBed:)
				(DoAudio Play 7066)
				(thePeas
					setCel: 1
					setStep: 15 15
					moveSpeed: 0
					setPri: 4
					setMotion: MoveTo (theHenchMan x?) (+ (theHenchMan y?) 4) self
				)
				(if (and (== curRoomNum 59) (> (theHenchMan y?) 189))
					(theHenchMan setMotion: MoveTo (theHenchMan x?) 189)
				else
					(theHenchMan setMotion: 0)
				)
				(ego setCycle: BegLoop)
			)
			(5
				(if (theHenchMan mover?)
					(-- state)
					(= cycles 1)
				else
					(theAudio number: 7066 loop: 1 play: self)
					(theHenchMan
						view: (if (== curRoomNum 58) 906 else 894)
						setLoop: (if (< (ego x?) (theHenchMan x?)) 1 else 0)
						posn: (theHenchMan x?) (- (theHenchMan y?) 5)
						setCycle: Forward
						cycleSpeed: 2
						ignoreActors: FALSE
					)
					(thePeas hide:)
				)
			)
			(6
				(if (== curRoomNum 59)
					(= henchmanState 8)
				else
					(= henchmanState 7)
				)
				(theAudio number: 8892 loop: 1 play:)
				(theHenchMan
					ignoreActors: TRUE
					setLoop: (if (< (ego x?) (theHenchMan x?)) 2 else 3)
					cel: 0
					setCycle: EndLoop self
				)
				(= gTheHenchManX (theHenchMan x?))
				(= gTheHenchManY (theHenchMan y?))
				(cond 
					((== curRoomNum 58)
						(= henchmanRectLeft (- (theHenchMan brLeft?) 5))
						(= henchmanRectTop (- (theHenchMan brTop?) 5))
						(= henchmanRectRight (+ (theHenchMan brRight?) 2))
						(= henchmanRectBottom (+ (theHenchMan brBottom?) 2))
					)
					((== (theHenchMan loop?) 2)
						(= henchmanRectLeft (- (theHenchMan brLeft?) 21))
						(= henchmanRectTop (- (theHenchMan brTop?) 5))
						(= henchmanRectRight (+ (theHenchMan brRight?) 1))
						(= henchmanRectBottom (+ (theHenchMan brBottom?) 2))
					)
					(else
						(= henchmanRectLeft (- (theHenchMan brLeft?) 1))
						(= henchmanRectTop (- (theHenchMan brTop?) 5))
						(= henchmanRectRight (+ (theHenchMan brRight?) 19))
						(= henchmanRectBottom (+ (theHenchMan brBottom?) 2))
					)
				)
				(= [henchmanPts 0] (= [henchmanPts 6] henchmanRectLeft))
				(= [henchmanPts 1] (= [henchmanPts 3] henchmanRectTop))
				(= [henchmanPts 2] (= [henchmanPts 4] henchmanRectRight))
				(= [henchmanPts 5] (= [henchmanPts 7] henchmanRectBottom))
				(henchmanPoly points: @henchmanPts size: 4)
				(curRoom addObstacle: henchmanPoly)
			)
			(7
				(CastleHandsOn)
				(ego
					get: 24
					view: (if (== curRoomNum 58) 34 else 0)
					normal: TRUE
					ignoreActors: FALSE
					cycleSpeed: 0
				)
				((ego head?) show:)
				(theMusic number: 836 loop: -1 playBed:)
				(client setScript: 0)
			)
		)
	)
)

(instance theFish of Actor
	(properties
		view 321
		loop 2
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
	)
)

(instance theWizard of Actor
	(properties
		signal (| ignrAct ignrHrz)
		detailLevel 3
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbDo
					(SpeakAudio 44)
					(event claimed: TRUE)
				)
				(verbTalk
					(switch wizardState
						(5 (SpeakAudio 53))
					)
					(event claimed: TRUE)
				)
				(verbLook
					(switch wizardState
						(5
							(if (== curRoomNum 64)
								(SpeakAudio 34)
							else
								(SpeakAudio 35)
							)
						)
					)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance theThrowFishScript of Script
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: iCatFish)
				(CastleHandsOff)
				(ego setMotion: 0)
				(Load SCRIPT JUMP)
				(Load VIEW 321)
				(theFish
					init:
					x: (if (< (ego x?) 163)
						(+ (ego x?) 24)
					else
						(- (ego x?) 23)
					)
					y: (- (ego y?) 15)
					cel: (if (< (ego x?) 163) 4 else 6)
					setCycle: Walk
					hide:
				)
				(Bset fCastleTimeStopped)
				(= cycles 1)
			)
			(1
				((ego head?) hide:)
				(ego
					view: 321
					normal: 0
					illegalBits: 0
					ignoreActors: 1
					setLoop: (if (< (ego x?) 163) 0 else 1)
					cycleSpeed: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if (< (ego x?) 163)
					((ScriptID 550 6)
						show:
						setMotion: JumpTo global357 global358 self
					)
				else
					((ScriptID 550 6)
						show:
						setMotion: JumpTo global355 global356 self
					)
					(theAudio number: 9301 play:)
				)
			)
			(3
				(ego view: 0 normal: TRUE)
				((ego head?) show:)
				(theFish setCycle: 0)
				(Bset fGaveCatFish)
				(theCat setScript: catGetFish)
				(client setScript: 0)
			)
		)
	)
)

(instance theCatScript of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theCat
					ignoreActors: TRUE
					cycleSpeed: 2
					setLoop: 9
					setCycle: EndLoop self
				)
			)
			(1
				(= seconds (Random 1 3))
			)
			(2
				(if (not (Random 0 1))
					(theCat setLoop: 9 setCycle: BegLoop self)
				else
					(self init:)
				)
			)
			(3
				(= seconds (Random 1 3))
			)
			(4
				(if (not (Random 0 1))
					(theCat setLoop: 0 cel: 0 setCycle: EndLoop self)
				else
					(self init:)
				)
			)
			(5
				(= seconds (Random 1 3))
			)
			(6
				(if (not (Random 0 1))
					(theCat setLoop: 2 cel: 0 setCycle: EndLoop self)
				else
					(if (not local7)
						(theCat
							setLoop: 6
							setCycle: Forward
							cycleSpeed: 1
							moveSpeed: 2
							setMotion: MoveTo (+ theCatX 50) theCatY self
						)
						(= local7 1)
					else
						(theCat
							setLoop: 7
							setCycle: Forward
							cycleSpeed: 1
							moveSpeed: 2
							setMotion: MoveTo theCatX theCatY self
						)
						(= local7 0)
					)
					(= state 90)
				)
			)
			(7 (= seconds (Random 2 4)))
			(8
				(if (and (!= catState 2) (not (curRoom script?)))
					(theCat setScript: theCatRunScript)
				else
					(self init:)
				)
			)
			(91
				(if local7
					(theCat setLoop: 0 setCel: 255)
				else
					(theCat setLoop: 1 setCel: 255)
				)
				(= cycles (Random 10 20))
			)
			(92 (theCat setCycle: BegLoop self))
			(93 (self init:))
		)
	)
)

(instance theCat of Actor
	(properties
		view 910
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreHorizon: TRUE setScript: theCatScript show:)
		(if (!= henchmanState 8)
			(= henchmanState 0)
		)
		(= theCatX (theCat x?))
		(= theCatY (theCat y?))
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(< (ego distanceTo: self) 15)
				(not (OneOf catState 7 5 2))
			)
			(Face ego self 5)
			(self setScript: theCatRunScript)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(switch catState
						(2 (SpeakAudio 36))
						(3 (SpeakAudio 37))
						(4 (SpeakAudio 38))
						(6 (SpeakAudio 39))
						(7 (SpeakAudio 40))
						(else  (SpeakAudio 41))
					)
					(event claimed: TRUE)
				)
				(verbDo
					(if (== catState 7)
						(SpeakAudio 45)
					else
						(SpeakAudio 46)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iCatFish
							(= catState 2)
							(curRoom setScript: theThrowFishScript)
						)
						(iPeas
							(cond 
								((not (Btst fThrewPeas))
									(SpeakAudio 50)
								)
								((not (Btst fGaveCatFish))
									(theCat setScript: (ScriptID 550 14))
								)
								(else
									(= catState 5)
									(theCat setScript: 0)
									(curRoom setScript: theBagCatScript)
								)
							)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else
							(SpeakAudio 51)
						)
					)
					(event claimed: TRUE)
				)
				(verbTalk
					(if (!= catState 7)
						(theCat setScript: theCatRunScript)
					else
						(SpeakAudio 5800)
					)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance catGetFish of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= catState 2)
				(theCat
					setLoop: (if (< (theCat x?) (theFish x?)) 4 else 5)
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: PolyPath (theFish x?) (theFish y?) self
				)
			)
			(1
				(= catState 3)
				(theFish hide:)
				(CastleHandsOn)
				(theCat setLoop: 10 cel: 0 setCycle: EndLoop self)
			)
			(2
				(theCat
					setLoop: 11
					setCycle: Forward
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(3
				(theCat
					setLoop: 12
					setCycle: Forward
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(4
				(theCat
					setLoop: 11
					setCycle: Forward
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(5 (= seconds 9))
			(6
				(= catState 4)
				(theFish dispose:)
				(theCat cycleSpeed: 0 setScript: theCatRunScript)
			)
		)
	)
)

(instance theBagCatScript of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego put: iPeas)
				(CastleHandsOff)
				(if (< (ego x?) (theCat x?))
					(= register 0)
					(ego
						illegalBits: 0
						ignoreActors: TRUE
						setMotion: PolyPath (- (theCat x?) 27) (theCat y?) self
					)
				else
					(= register 1)
					(ego
						illegalBits: 0
						ignoreActors: TRUE
						setMotion: PolyPath (+ (theCat x?) 19) (theCat y?) self
					)
				)
			)
			(1
				(if (== (DoAudio Loc) -1)
					(theAudio number: 7063 loop: 1 play:)
				)
				(SolvePuzzle 2)
				(ego
					normal: 0
					view: 914
					cel: 0
					setLoop: register
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				((ego head?) hide:)
				(theFish hide:)
				(theCat hide:)
			)
			(2
				(ego
					setLoop: (+ (ego loop?) 2)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (== curRoomNum 63)
					(= global359 91)
				)
				(= catState 7)
				(= catRoom curRoomNum)
				(= baggedCatX ((ScriptID 550 2) x?))
				(= baggedCatY ((ScriptID 550 2) y?))
				(theCat view: 914 setScript: catInBag ignoreActors: FALSE)
				(ego normal: TRUE view: 0)
				((ego head?) show:)
				(= cycles 3)
			)
			(4
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance theCatRunScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(theMusic stop:)
				(theMusic3 number: 837 loop: -1 playBed:)
				(if (not (curRoom script?))
					(ego setMotion: 0)
				)
				(if (== catState 4)
					(self cue:)
				else
					(= catState 5)
					(if (< (ego x?) (theCat x?))
						(proc762_0 @local42 @local24 @local60)
					else
						(proc762_0 @local51 @local33 @local60)
					)
				)
				(= cycles 1)
			)
			(1
				(theCat
					setCycle: Forward
					cycleSpeed: (if (== curRoomNum 64) 2 else 0)
					moveSpeed: (if (== curRoomNum 64) 2 else 0)
					ignoreActors: TRUE
					illegalBits: 0
					ignoreHorizon: TRUE
				)
				(= cycles 1)
			)
			(2
				(theMusic3 fade:)
				(switch curRoomNum
					(57
						(theCat setLoop: 4 setMotion: MoveTo 304 143 self)
					)
					(59
						(theCat setLoop: 5 setMotion: MoveTo -1 169 self)
					)
					(60
						(theCat setLoop: 5 setMotion: MoveTo 0 161 self)
					)
					(61
						(theCat setLoop: 4 setMotion: MoveTo 319 173 self)
					)
					(63
						(theCat setLoop: 4 setMotion: MoveTo 317 144 self)
					)
					(64
						(theCat setLoop: 13 setMotion: MoveTo 156 120 self)
					)
				)
			)
			(3
				(CastleHandsOn)
				(= henchmanState 0)
				(= catState 6)
				(= wizardState 2)
				(theCat posn: 1000 1000 stopUpd:)
				(if henchmanTimer
					(= henchmanTimer 3)
				else
					(= wizardTimer (Random 5 10))
				)
				(theMusic number: 836 loop: -1 playBed:)
			)
		)
	)
)

(instance catInBag of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theCat
					view: 914
					setLoop: 4
					posn: baggedCatX baggedCatY
					show:
				)
				(= cycles 1)
			)
			(1
				(= start state)
				(if (Random 0 1)
					(theCat setLoop: 4)
				else
					(theCat setLoop: 5)
				)
				(if (Random 0 1)
					(theCat setCycle: Forward cycleSpeed: (Random 1 4))
				else
					(theCat setCycle: Reverse cycleSpeed: (Random 1 4))
				)
				(= seconds (Random 2 5))
			)
			(2
				(if (Random 0 1)
					(theCat setCycle: 0)
				else
					(theCat cycleSpeed: (Random 1 4))
				)
				(= seconds (Random 2 5))
			)
			(3 (self init:))
		)
	)
)

(instance blockOne of Block
	(properties
		top 1000
		left -1000
		bottom 1002
		right -1002
	)
)

(instance henchmanPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance theHenchManScript of Script

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					((and (== catState 7) (== catRoom curRoomNum))
						(client setScript: 0)
					)
					((== curRoomNum 67)
						(client setScript: 0)
					)
					((== henchmanState 3)
						(switch curRoomNum
							(59
								(= cycles 1)
							)
							(else
								(= seconds 2)
							)
						)
					)
					(else
						(switch curRoomNum
							(58 (= seconds (Random 2 4)))
							(else 
								(= seconds (Random 4 10))
							)
						)
					)
				)
			)
			(1
				(cond 
					(
						(and
							(== curRoomNum 58)
							(> (ego x?) 160)
							(< (ego x?) 205)
						)
						(theMagicDoor
							init:
							posn: 160 113
							setPri: 8
							setLoop: 2
							hide:
						)
						(theRings init: setLoop: 2 setPri: 9 posn: 159 89 hide:)
						(theHenchMan posn: 319 137)
						(= cycles 1)
					)
					((and (== curRoomNum 59) (< (ego y?) 170))
						(theMagicDoor
							init:
							setPri: 4
							posn: 310 179
							setLoop: 1
							hide:
						)
						(theRings init: setLoop: 1 setPri: 5 posn: 310 131 hide:)
						(if (< (ego x?) 159)
							(theHenchMan posn: 256 260)
							(= global345 256)
							(= global346 195)
						else
							(theHenchMan posn: 75 260)
							(= global345 75)
							(= global346 195)
						)
						(= cycles 1)
					)
					((and (== curRoomNum 60) (> (ego x?) 79))
						(theHenchMan posn: -52 160)
						(= cycles 1)
					)
					((and (== curRoomNum 61) (< (ego x?) 255))
						(theMagicDoor
							init:
							setPri: 3
							posn: 31 163
							setLoop: 0
							hide:
						)
						(theRings init: setLoop: 0 setPri: 4 posn: 31 115 hide:)
						(theHenchMan
							setPri: (if (== curRoom 61) 5 else -1)
							posn: 354 173
						)
						(= cycles 1)
					)
					((and (== curRoomNum 54) (< (ego y?) 157))
						(theMagicDoor
							init:
							setPri: 5
							posn: 291 182
							setLoop: 1
							hide:
						)
						(theRings init: setLoop: 1 setPri: 6 posn: 291 134 hide:)
						(theHenchMan posn: 163 296)
						(= cycles 1)
					)
					(else
						(self init:)
					)
				)
			)
			(2
				(if (!= curRoomNum 58)
					(cond 
						((not (HaveMouse))
							(theHenchMan moveSpeed: 2)
						)
						((== (ego moveSpeed?) 0)
							(theHenchMan moveSpeed: 0)
						)
						(else
							(theHenchMan moveSpeed: 1)
						)
					)
					(theHenchMan setStep: 4 2)
				else
					(cond 
						((not (HaveMouse))
							(theHenchMan setStep: 2 1)
						)
						((== (ego moveSpeed?) 0)
							(theHenchMan setStep: 4 2)
						)
						(else
							(theHenchMan setStep: 3 2)
						)
					)
					(theHenchMan moveSpeed: 0)
				)
				(= cycles 1)
			)
			(3
				(Load SOUND 135)
				(Load SOUND 136)
				(= henchmanState 3)
				(theMusic number: 135 loop: -1 playBed:)
				(theHenchMan
					show:
					illegalBits: 0
					ignoreActors: TRUE
					ignoreHorizon: TRUE
					setMotion: MoveTo global345 global346 self
				)
				(if (== curRoomNum 59)
					(ego setMotion: 0)
				)
			)
			(4
				(= henchmanState 3)
				(if (== curRoomNum 58)
					(theHenchMan setAvoider: (Avoider new:))
				)
				(theHenchMan illegalBits: 0 setMotion: PChase ego 20 self)
			)
			(5
				(if (not (Btst fCaughtByHenchman))
					(Bset fCaughtByHenchman)
					(SolvePuzzle 2)
				)
				(= henchmanState 4)
				(CastleHandsOff)
				(curRoom setScript: 0)
				(ego setMotion: 0 hide:)
				(if (!= curRoomNum 60)
					(theAudio number: 8018 loop: 1 play:)
					(theMagicDoor show: cycleSpeed: 1 setCycle: EndLoop)
				)
				(if (< (ego x?) (theHenchMan x?))
					(theHenchMan
						view: (if (== curRoomNum 58) 900 else 888)
						setLoop: 1
						cel: 0
						cycleSpeed: 3
						setCycle: EndLoop self
					)
				else
					(theHenchMan
						view: 900
						setLoop: 0
						cycleSpeed: 3
						cel: 0
						setCycle: EndLoop self
					)
				)
				((ego head?) hide:)
			)
			(6
				(theHenchMan
					view: (if (== curRoomNum 58) 908 else 896)
					setLoop: -1
					setCycle: Walk
					setStep: 4 2
					setPri: (if (== curRoomNum 58) 9 else -1)
					cycleSpeed: 0
					illegalBits: 0
					ignoreActors: 1
					setMotion: PolyPath magicDoorX magicDoorY self
				)
			)
			(7
				(if (== curRoomNum 60)
					(= henchmanState 5)
					(curRoom newRoom: 59)
				else
					(theHenchMan
						setLoop:
						(switch curRoomNum
							(54 0)
							(58 3)
							(59 0)
							(60 0)
							(61 2)
						)
					)
					(theRings show: cycleSpeed: 1 setScript: ringsScript)
					(= cycles 5)
				)
			)
			(8
				(theHenchMan
					view: 904
					setLoop:
					(switch curRoomNum
						(54 1)
						(58 0)
						(59 1)
						(60 1)
						(61 2)
					)
					cel: 0
					cycleSpeed: 3
					setPri: 8
					setCycle: EndLoop self
				)
			)
			(9
				(theHenchMan hide:)
				(= seconds 4)
			)
			(10
				(theRings dispose:)
				(theAudio number: 8018 loop: 1 play:)
				(theMusic fade:)
				(theMagicDoor
					setCel: 255
					cycleSpeed: 2
					setCycle: BegLoop self
				)
			)
			(11
				(theMusic stop:)
				(theMagicDoor hide:)
				(= cycles 3)
			)
			(12 (curRoom newRoom: 67))
		)
	)
)

(instance theWizardScript of Script

	(method (doit)
		(super doit:)
		(if (or (== state 6) (== state 108))
			(cond 
				((not local14) (= local14 (GetTime SYSTIME1)))
				((> (- (GetTime 1) local14) 10) (= cycles 1) (= seconds 0))
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local14 0)
				(CastleHandsOff)
				(= wizardState 4)
				(if henchmanState (theHenchMan setScript: 0 setMotion: 0))
				(if (< (ego x?) wizardX)
					(= temp0 0)
				else
					(= temp0 1)
				)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setCel: temp0
					setMotion: 0
				)
				(theMusic stop:)
				(theMusic3
					number: 134
					loop: 1
					priority: 100
					vol: 127
					play:
				)
				(if (OneOf curRoomNum 58 62)
					(Load VIEW 701)
					(Load VIEW 704)
					(self changeState: 100)
				else
					(switch wizardAngle
						(135 (= temp0 0))
						(225 (= temp0 1))
						(45 (= temp0 2))
						(315 (= temp0 3))
					)
					(theWizard
						view: (if (== curRoomNum 65) 702 else 705)
						posn: wizardX wizardY
						setLoop: temp0
						cel: 0
						show:
						cycleSpeed: 1
						setCycle: EndLoop self
					)
					(Face ego theWizard 5)
				)
			)
			(1
				(switch wizardAngle
					(135 (= temp0 4))
					(225 (= temp0 5))
					(45 (= temp0 6))
					(315 (= temp0 7))
				)
				(theWizard setLoop: temp0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(proc762_1 @local15 926 self)
			)
			(3
				(theMusic number: 835 loop: 1 playBed: self)
				(switch wizardAngle
					(135
						(= temp0 8)
						(= local1 19)
						(= local2 -41)
					)
					(225
						(= temp0 9)
						(= local1 -21)
						(= local2 -41)
					)
					(45
						(= temp0 10)
						(= local1 13)
						(= local2 -42)
					)
					(315
						(= temp0 11)
						(= local1 -16)
						(= local2 -42)
					)
				)
				(if (== curRoomNum 65)
					(= local2 -28)
					(if (< (ego x?) 144)
						(= local1 -16)
					else
						(= local1 16)
					)
				)
				(theWizard
					setLoop: temp0
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo 6 1 self
				)
			)
			(4
				(theAura
					init:
					setCycle: Forward
					posn:
						(+ ((ScriptID 550 7) x?) local1)
						(+ ((ScriptID 550 7) y?) local2)
				)
				(= seconds 2)
			)
			(5
				((ego head?) hide:)
				(switch global354
					(135 (= temp0 0))
					(225 (= temp0 1))
					(45 (= temp0 6))
					(315 (= temp0 7))
				)
				(ego
					normal: 0
					view: (if (== curRoomNum 65) 127 else 132)
					setLoop: temp0
					setCycle: EndLoop self
					cycleSpeed: 3
				)
			)
			(6
				(theAudio number: 7058 loop: 1 play:)
				(ego
					setLoop: (+ (ego loop?) 2)
					setCycle: Forward
					cycleSpeed: 3
				)
			)
			(7
				(theAudio stop:)
				(theAura hide:)
				(theWizard setCel: 255)
				(switch global354
					(135 (= temp0 4))
					(225 (= temp0 5))
					(45 (= temp0 10))
					(315 (= temp0 11))
				)
				(ego setLoop: temp0 cel: 0 setCycle: EndLoop self)
			)
			(8
				(theWizard setCycle: BegLoop)
				(= seconds 4)
				(= global103 0)
			)
			(9
				(= deathMessage 657)
				(EgoDead 73 0 -1)
			)
			(100
				(theWizard
					view: 701
					posn: wizardX wizardY
					setLoop: (if (== wizardAngle 90) 1 else 0)
					cel: 0
					show:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(Face ego theWizard 5)
			)
			(101
				(theWizard
					setLoop: (if (== wizardAngle 90) 3 else 2)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(102 (= seconds 2))
			(103
				(theMusic number: 835 loop: 1 playBed: self)
				(theWizard
					setLoop: (if (== wizardAngle 90) 5 else 4)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(104
				(proc762_1 @local15 926 self)
			)
			(105
				(if (== wizardAngle 90)
					(= temp0 7)
					(= local1 -28)
					(= local2 -30)
				else
					(= temp0 6)
					(= local1 25)
					(= local2 -30)
				)
				(theWizard
					setLoop: temp0
					cel: 0
					cycleSpeed: 3
					setCycle: CycleTo 6 1 self
				)
			)
			(106
				(theAura
					init:
					setCycle: Forward
					posn:
						(+ ((ScriptID 550 7) x?) local1)
						(+ ((ScriptID 550 7) y?) local2)
				)
				(= seconds 1)
			)
			(107
				((ego head?) hide:)
				(ego
					normal: 0
					view: 704
					setLoop: (if (== global354 90) 0 else 1)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(108
				(theAudio number: 7058 loop: 1 play:)
				(ego
					setLoop: (if (== global354 90) 3 else 2)
					setCycle: Forward
					cycleSpeed: 2
				)
			)
			(109
				(theAura hide:)
				(theWizard setCel: 255)
				(ego
					setLoop: (if (== global354 90) 4 else 5)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(110
				(theAudio stop:)
				(theWizard cycleSpeed: 2 setCycle: BegLoop)
				(= seconds 3)
				(= global103 0)
			)
			(111
				(= deathMessage 657)
				(EgoDead 73 0 -1)
			)
		)
	)
)

(instance ringsScript of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (< (DoSound NumVoices) 8)
					(theRings setCycle: EndLoop self)
				else
					(theAudio number: 8071 loop: 1 play: self)
					((ScriptID 550 5) setCycle: EndLoop)
				)
			)
			(1 (= cycles 5))
			(2 (self init:))
		)
	)
)
