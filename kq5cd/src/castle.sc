;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include sci.sh)
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
	proc550_16 16
	proc550_17 17
	proc550_18 18
	proc550_19 19
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
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	[local15 9] = [1002 116 63 4 11 29 20 28 29]
	[local24 9] = [1003 10 10 4 11 25 23 31 31]
	[local33 9] = [1003 160 20 4 11 25 23 31 31]
	[local42 9] = [1038 160 20 2 11 22 31 16 37]
	[local51 9] = [1038 10 10 2 11 22 31 16 37]
	[local60 8] = [0 5801 1 7016 0 5803]
)
(procedure (proc550_16)
	(if (and (!= global332 7) (> (Random 0 100) 20))
		(Load rsSOUND 835)
		(switch curRoomNum
			(57 (theCat posn: 91 172 init:))
			(58
				(theCat posn: 103 115 init:)
			)
			(59
				(theCat posn: 209 162 init:)
			)
			(60 (theCat posn: 88 149 init:))
			(61
				(theCat posn: 138 144 init:)
			)
			(63 (theCat posn: 88 169 init:))
			(64
				(theCat posn: 170 173 init:)
			)
		)
		(= gGNumber_3 curRoomNum)
		(= global332 1)
	)
)

(procedure (proc550_17)
	(HandsOff)
)

(procedure (proc550_18)
	(HandsOn)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		moveSpeed: (theGame egoMoveSpeed?)
		setCycle: KQ5SyncWalk
		setStep: 3 2
		normal: 1
		illegalBits: -32768
		ignoreActors: 0
	)
)

(procedure (proc550_19)
	(cond 
		((< (theGame egoMoveSpeed?) 3) (ego cycleSpeed: 2))
		((< (theGame egoMoveSpeed?) 6) (ego cycleSpeed: 4))
		(else (ego cycleSpeed: 6))
	)
)

(class castle of Rgn
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		lookStr 0
	)
	
	(method (init)
		(super init: &rest)
		(if
		(and (== global332 7) (== gGNumber_3 curRoomNum))
			(theCat init: ignoreActors: 0 setScript: catInBag)
		)
		(if (== curRoomNum 58)
			(Load rsVIEW 898)
		else
			(Load rsVIEW 884)
		)
		(if
			(and
				(< global333 4)
				(!= (theMusic number?) 836)
				(not (OneOf curRoomNum 54 67 96 57))
			)
			(theMusic number: 836 loop: -1 playBed:)
		)
		(Bclr 64)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
		(and (not (Btst 64)) (!= local8 (GetTime 1)))
			(= local8 (GetTime 1))
			(= local5 1)
		)
		(if local5
			(= local5 0)
			(if (> global352 1)
				(if (== (-- global352) 2)
					(switch curRoomNum
						(58
							(Load rsVIEW 701)
							(Load rsVIEW 704)
						)
						(65
							(Load rsVIEW 702)
							(Load rsVIEW 127)
						)
						(else 
							(Load rsVIEW 705)
							(Load rsVIEW 132)
						)
					)
				)
				(if (== global352 1) (= global331 3) (Bset 64))
			)
			(if (and global353 (not (-- global353)))
				(if (not global333)
					(if (== curRoomNum 63)
						(= global331 6)
					else
						(= global352 3)
					)
				else
					(= global353 5)
				)
			)
			(if (and local6 (not (-- local6))) (proc550_18))
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(DisposeScript 969)
		(DisposeScript 972)
		(DisposeScript 991)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(= initialized 0)
		(theCat setScript: 0 dispose:)
		(theHenchMan setScript: 0 dispose:)
		(theWizard setScript: 0 dispose:)
		(if (and (!= global332 7) (== global332 1))
			(= global333 0)
			(= global332 6)
			(= global331 2)
			(if global353 (= global353 3) else (= global352 10))
		)
		(super newRoom: n &rest)
	)
)

(instance theMagicDoor of Prop
	(properties
		view 695
		signal $6800
	)
)

(instance theAura of Prop
	(properties
		view 703
		priority 15
		signal $6810
	)
)

(instance theRings of Prop
	(properties
		view 697
		signal $6800
	)
)

(instance theHenchMan of Actor
	(properties
		x 1000
		y 1000
		signal $6000
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
		(if (not global333) (= global333 1))
		(if (!= global332 7) (= gGNumber_3 0))
		(if (and (== curRoomNum 59) (== global333 8))
			(henchmanPoly points: @global366 size: 4)
			(curRoom addObstacle: henchmanPoly)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(switch global333
						(3 (SpeakAudio 32))
						(7 (SpeakAudio 33))
						(8 (SpeakAudio 33))
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (and (!= global333 7) (!= global333 8))
						(SpeakAudio 42)
					else
						(SpeakAudio 43)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(24
							(cond 
								((== global333 4) (event claimed: 0))
								((== ((inventory at: 24) cel?) 4)
									(proc550_17)
									(= local6 2)
									(SpeakAudio 47)
									(proc550_18)
									(event claimed: 1)
								)
								(
								(and (< (ego distanceTo: self) 70) (!= global333 7))
									(proc550_17)
									(= local6 2)
									(SpeakAudio 48)
									(event claimed: 1)
								)
								(else
									(curRoom setScript: theThrowPeasScript)
									(event claimed: 1)
								)
							)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 49)
							(event claimed: 1)
						)
					)
				)
				(JOY_DOWN
					(SpeakAudio 52)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance thePeas of Actor
	(properties
		view 909
		signal $6000
	)
)

(instance theThrowPeasScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if (!= global333 3) (self init:) else (self cue:))
			)
			(2
				(ego put: 24)
				(proc550_17)
				(ego setMotion: 0)
				(SolvePuzzle 3)
				(Bset 63)
				((inventory at: 24)
					cel: (+ 1 ((inventory at: 24) cel?))
					cursor: theEmptyBagCursor
					yourself:
				)
				(ego put: 24 curRoomNum)
				(ego get: 24)
				(theHenchMan setScript: 0)
				((ego head?) hide:)
				(ego
					view: (if (== curRoomNum 58) 907 else 909)
					normal: 0
					setCel: 0
					illegalBits: 0
					ignoreActors: 1
					setLoop: (if (< (ego x?) (theHenchMan x?)) 0 else 1)
					cycleSpeed: 2
					setCycle: End self
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
				(DoAudio 1 7066)
				(thePeas
					setCel: 1
					setStep: 15 15
					moveSpeed: 0
					setPri: 4
					setMotion: MoveTo (theHenchMan x?) (+ (theHenchMan y?) 4) self
				)
				(if
				(and (== curRoomNum 59) (> (theHenchMan y?) 189))
					(theHenchMan setMotion: MoveTo (theHenchMan x?) 189)
				else
					(theHenchMan setMotion: 0)
				)
				(ego setCycle: Beg)
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
						setCycle: Fwd
						cycleSpeed: 2
						ignoreActors: 0
					)
					(thePeas hide:)
				)
			)
			(6
				(if (== curRoomNum 59)
					(= global333 8)
				else
					(= global333 7)
				)
				(theAudio number: 8892 loop: 1 play:)
				(theHenchMan
					ignoreActors: 1
					setLoop: (if (< (ego x?) (theHenchMan x?)) 2 else 3)
					cel: 0
					setCycle: End self
				)
				(= gTheHenchManX (theHenchMan x?))
				(= gTheHenchManY (theHenchMan y?))
				(cond 
					((== curRoomNum 58)
						(= local9 (- (theHenchMan brLeft?) 5))
						(= local10 (- (theHenchMan brTop?) 5))
						(= local11 (+ (theHenchMan brRight?) 2))
						(= local12 (+ (theHenchMan brBottom?) 2))
					)
					((== (theHenchMan loop?) 2)
						(= local9 (- (theHenchMan brLeft?) 21))
						(= local10 (- (theHenchMan brTop?) 5))
						(= local11 (+ (theHenchMan brRight?) 1))
						(= local12 (+ (theHenchMan brBottom?) 2))
					)
					(else
						(= local9 (- (theHenchMan brLeft?) 1))
						(= local10 (- (theHenchMan brTop?) 5))
						(= local11 (+ (theHenchMan brRight?) 19))
						(= local12 (+ (theHenchMan brBottom?) 2))
					)
				)
				(= [global366 0] (= [global366 6] local9))
				(= [global366 1] (= [global366 3] local10))
				(= [global366 2] (= [global366 4] local11))
				(= [global366 5] (= [global366 7] local12))
				(henchmanPoly points: @global366 size: 4)
				(curRoom addObstacle: henchmanPoly)
			)
			(7
				(proc550_18)
				(ego
					get: 24
					view: (if (== curRoomNum 58) 34 else 0)
					normal: 1
					ignoreActors: 0
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
		signal $6800
		illegalBits $0000
	)
)

(instance theWizard of Actor
	(properties
		signal $6000
		detailLevel 3
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_RIGHT
					(SpeakAudio 44)
					(event claimed: 1)
				)
				(JOY_DOWN
					(switch global331
						(5 (SpeakAudio 53))
					)
					(event claimed: 1)
				)
				(JOY_UPRIGHT
					(switch global331
						(5
							(if (== curRoomNum 64)
								(SpeakAudio 34)
							else
								(SpeakAudio 35)
							)
						)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance theThrowFishScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 37)
				(proc550_17)
				(ego setMotion: 0)
				(Load rsSCRIPT 991)
				(Load rsVIEW 321)
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
				(Bset 64)
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
					setCycle: End self
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
				(ego view: 0 normal: 1)
				((ego head?) show:)
				(theFish setCycle: 0)
				(Bset 62)
				(theCat setScript: catGetFish)
				(client setScript: 0)
			)
		)
	)
)

(instance theCatScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theCat
					ignoreActors: 1
					cycleSpeed: 2
					setLoop: 9
					setCycle: End self
				)
			)
			(1 (= seconds (Random 1 3)))
			(2
				(if (not (Random 0 1))
					(theCat setLoop: 9 setCycle: Beg self)
				else
					(self init:)
				)
			)
			(3 (= seconds (Random 1 3)))
			(4
				(if (not (Random 0 1))
					(theCat setLoop: 0 cel: 0 setCycle: End self)
				else
					(self init:)
				)
			)
			(5 (= seconds (Random 1 3)))
			(6
				(if (not (Random 0 1))
					(theCat setLoop: 2 cel: 0 setCycle: End self)
				else
					(if (not local7)
						(theCat
							setLoop: 6
							setCycle: Fwd
							cycleSpeed: 1
							moveSpeed: 2
							setMotion: MoveTo (+ theCatX 50) theCatY self
						)
						(= local7 1)
					else
						(theCat
							setLoop: 7
							setCycle: Fwd
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
				(if
				(and (!= global332 2) (not (curRoom script?)))
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
			(92 (theCat setCycle: Beg self))
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
		(self ignoreHorizon: 1 setScript: theCatScript show:)
		(if (!= global333 8) (= global333 0))
		(= theCatX (theCat x?))
		(= theCatY (theCat y?))
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(< (ego distanceTo: self) 15)
				(not (OneOf global332 7 5 2))
			)
			(Face ego self 5)
			(self setScript: theCatRunScript)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(switch global332
						(2 (SpeakAudio 36))
						(3 (SpeakAudio 37))
						(4 (SpeakAudio 38))
						(6 (SpeakAudio 39))
						(7 (SpeakAudio 40))
						(else  (SpeakAudio 41))
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== global332 7)
						(SpeakAudio 45)
					else
						(SpeakAudio 46)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(37
							(= global332 2)
							(curRoom setScript: theThrowFishScript)
						)
						(24
							(cond 
								((not (Btst 63)) (SpeakAudio 50))
								((not (Btst 62)) (theCat setScript: (ScriptID 550 14)))
								(else
									(= global332 5)
									(theCat setScript: 0)
									(curRoom setScript: theBagCatScript)
								)
							)
						)
						(28 (event claimed: 0))
						(else  (SpeakAudio 51))
					)
					(event claimed: 1)
				)
				(JOY_DOWN
					(if (!= global332 7)
						(theCat setScript: theCatRunScript)
					else
						(SpeakAudio 5800)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance catGetFish of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= global332 2)
				(theCat
					setLoop: (if (< (theCat x?) (theFish x?)) 4 else 5)
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: PolyPath (theFish x?) (theFish y?) self
				)
			)
			(1
				(= global332 3)
				(theFish hide:)
				(proc550_18)
				(theCat setLoop: 10 cel: 0 setCycle: End self)
			)
			(2
				(theCat
					setLoop: 11
					setCycle: Fwd
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(3
				(theCat
					setLoop: 12
					setCycle: Fwd
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(4
				(theCat
					setLoop: 11
					setCycle: Fwd
					cycleSpeed: (Random 3 6)
				)
				(= seconds (Random 1 3))
			)
			(5 (= seconds 9))
			(6
				(= global332 4)
				(theFish dispose:)
				(theCat cycleSpeed: 0 setScript: theCatRunScript)
			)
		)
	)
)

(instance theBagCatScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego put: 24)
				(proc550_17)
				(if (< (ego x?) (theCat x?))
					(= register 0)
					(ego
						illegalBits: 0
						ignoreActors: 1
						setMotion: PolyPath (- (theCat x?) 27) (theCat y?) self
					)
				else
					(= register 1)
					(ego
						illegalBits: 0
						ignoreActors: 1
						setMotion: PolyPath (+ (theCat x?) 19) (theCat y?) self
					)
				)
			)
			(1
				(if (== (DoAudio 6) -1)
					(theAudio number: 7063 loop: 1 play:)
				)
				(SolvePuzzle 2)
				(ego
					normal: 0
					view: 914
					cel: 0
					setLoop: register
					cycleSpeed: 2
					setCycle: End self
				)
				((ego head?) hide:)
				(theFish hide:)
				(theCat hide:)
			)
			(2
				(ego
					setLoop: (+ (ego loop?) 2)
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if (== curRoomNum 63) (= global359 91))
				(= global332 7)
				(= gGNumber_3 curRoomNum)
				(= global339 ((ScriptID 550 2) x?))
				(= global340 ((ScriptID 550 2) y?))
				(theCat view: 914 setScript: catInBag ignoreActors: 0)
				(ego normal: 1 view: 0)
				((ego head?) show:)
				(= cycles 3)
			)
			(4
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)

(instance theCatRunScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc550_17)
				(theMusic stop:)
				(theMusic3 number: 837 loop: -1 playBed:)
				(if (not (curRoom script?)) (ego setMotion: 0))
				(if (== global332 4)
					(self cue:)
				else
					(= global332 5)
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
					setCycle: Fwd
					cycleSpeed: (if (== curRoomNum 64) 2 else 0)
					moveSpeed: (if (== curRoomNum 64) 2 else 0)
					ignoreActors: 1
					illegalBits: 0
					ignoreHorizon: 1
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
				(proc550_18)
				(= global333 0)
				(= global332 6)
				(= global331 2)
				(theCat posn: 1000 1000 stopUpd:)
				(if global353
					(= global353 3)
				else
					(= global352 (Random 5 10))
				)
				(theMusic number: 836 loop: -1 playBed:)
			)
		)
	)
)

(instance catInBag of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theCat
					view: 914
					setLoop: 4
					posn: global339 global340
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
					(theCat setCycle: Fwd cycleSpeed: (Random 1 4))
				else
					(theCat setCycle: Rev cycleSpeed: (Random 1 4))
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

(instance blockOne of Blk
	(properties
		top 1000
		left -1000
		bottom 1002
		right -1002
	)
)

(instance henchmanPoly of Polygon
	(properties
		type $0002
	)
)

(instance theHenchManScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					(
					(and (== global332 7) (== gGNumber_3 curRoomNum)) (client setScript: 0))
					((== curRoomNum 67) (client setScript: 0))
					((== global333 3)
						(switch curRoomNum
							(59 (= cycles 1))
							(else  (= seconds 2))
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
					((and (== curRoomNum 60) (> (ego x?) 79)) (theHenchMan posn: -52 160) (= cycles 1))
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
					(else (self init:))
				)
			)
			(2
				(if (!= curRoomNum 58)
					(cond 
						((not (HaveMouse)) (theHenchMan moveSpeed: 2))
						((== (ego moveSpeed?) 0) (theHenchMan moveSpeed: 0))
						(else (theHenchMan moveSpeed: 1))
					)
					(theHenchMan setStep: 4 2)
				else
					(cond 
						((not (HaveMouse)) (theHenchMan setStep: 2 1))
						((== (ego moveSpeed?) 0) (theHenchMan setStep: 4 2))
						(else (theHenchMan setStep: 3 2))
					)
					(theHenchMan moveSpeed: 0)
				)
				(= cycles 1)
			)
			(3
				(Load rsSOUND 135)
				(Load rsSOUND 136)
				(= global333 3)
				(theMusic number: 135 loop: -1 playBed:)
				(theHenchMan
					show:
					illegalBits: 0
					ignoreActors: 1
					ignoreHorizon: 1
					setMotion: MoveTo global345 global346 self
				)
				(if (== curRoomNum 59) (ego setMotion: 0))
			)
			(4
				(= global333 3)
				(if (== curRoomNum 58)
					(theHenchMan setAvoider: (Avoid new:))
				)
				(theHenchMan illegalBits: 0 setMotion: PChase ego 20 self)
			)
			(5
				(if (not (Btst 77)) (Bset 77) (SolvePuzzle 2))
				(= global333 4)
				(proc550_17)
				(curRoom setScript: 0)
				(ego setMotion: 0 hide:)
				(if (!= curRoomNum 60)
					(theAudio number: 8018 loop: 1 play:)
					(theMagicDoor show: cycleSpeed: 1 setCycle: End)
				)
				(if (< (ego x?) (theHenchMan x?))
					(theHenchMan
						view: (if (== curRoomNum 58) 900 else 888)
						setLoop: 1
						cel: 0
						cycleSpeed: 3
						setCycle: End self
					)
				else
					(theHenchMan
						view: 900
						setLoop: 0
						cycleSpeed: 3
						cel: 0
						setCycle: End self
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
					setMotion: PolyPath global347 global348 self
				)
			)
			(7
				(if (== curRoomNum 60)
					(= global333 5)
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
					setCycle: End self
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
					setCycle: Beg self
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
	(properties)
	
	(method (doit)
		(super doit:)
		(if (or (== state 6) (== state 108))
			(cond 
				((not local14) (= local14 (GetTime 1)))
				((> (- (GetTime 1) local14) 10) (= cycles 1) (= seconds 0))
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local14 0)
				(proc550_17)
				(= global331 4)
				(if global333 (theHenchMan setScript: 0 setMotion: 0))
				(if (< (ego x?) global349)
					(= temp0 0)
				else
					(= temp0 1)
				)
				(ego
					illegalBits: 0
					ignoreActors: 1
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
					(Load rsVIEW 701)
					(Load rsVIEW 704)
					(self changeState: 100)
				else
					(switch global351
						(135 (= temp0 0))
						(225 (= temp0 1))
						(45 (= temp0 2))
						(315 (= temp0 3))
					)
					(theWizard
						view: (if (== curRoomNum 65) 702 else 705)
						posn: global349 global350
						setLoop: temp0
						cel: 0
						show:
						cycleSpeed: 1
						setCycle: End self
					)
					(Face ego theWizard 5)
				)
			)
			(1
				(switch global351
					(135 (= temp0 4))
					(225 (= temp0 5))
					(45 (= temp0 6))
					(315 (= temp0 7))
				)
				(theWizard setLoop: temp0 cel: 0 setCycle: End self)
			)
			(2
				(proc762_1 @local15 926 self)
			)
			(3
				(theMusic number: 835 loop: 1 playBed: self)
				(switch global351
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
					setCycle: CT 6 1 self
				)
			)
			(4
				(theAura
					init:
					setCycle: Fwd
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
					setCycle: End self
					cycleSpeed: 3
				)
			)
			(6
				(theAudio number: 7058 loop: 1 play:)
				(ego
					setLoop: (+ (ego loop?) 2)
					setCycle: Fwd
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
				(ego setLoop: temp0 cel: 0 setCycle: End self)
			)
			(8
				(theWizard setCycle: Beg)
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
					posn: global349 global350
					setLoop: (if (== global351 90) 1 else 0)
					cel: 0
					show:
					cycleSpeed: 1
					setCycle: End self
				)
				(Face ego theWizard 5)
			)
			(101
				(theWizard
					setLoop: (if (== global351 90) 3 else 2)
					cel: 0
					setCycle: End self
				)
			)
			(102 (= seconds 2))
			(103
				(theMusic number: 835 loop: 1 playBed: self)
				(theWizard
					setLoop: (if (== global351 90) 5 else 4)
					cel: 0
					setCycle: End self
				)
			)
			(104
				(proc762_1 @local15 926 self)
			)
			(105
				(if (== global351 90)
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
					setCycle: CT 6 1 self
				)
			)
			(106
				(theAura
					init:
					setCycle: Fwd
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
					setCycle: End self
				)
			)
			(108
				(theAudio number: 7058 loop: 1 play:)
				(ego
					setLoop: (if (== global354 90) 3 else 2)
					setCycle: Fwd
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
					setCycle: End self
				)
			)
			(110
				(theAudio stop:)
				(theWizard cycleSpeed: 2 setCycle: Beg)
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
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (< (DoSound sndDISPOSE) 8)
					(theRings setCycle: End self)
				else
					(theAudio number: 8071 loop: 1 play: self)
					((ScriptID 550 5) setCycle: End)
				)
			)
			(1 (= cycles 5))
			(2 (self init:))
		)
	)
)
