;;; Sierra Script 1.0 - (do not remove this comment)
(script# 124)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Sync)
(use IconBar)
(use RandCyc)
(use LoadMany)
(use RFeature)
(use Path)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	battle 0
	getWand 2
)

(local
	[local0 15] = [198 49 182 40 171 38 158 38 146 41 136 51 122 70 -32768]
	[local15 25] = [7 3 7 6 6 6 6 6 5 9 3 9 1 9 1 9 0 12 0 12 0 12 0 10 -32768]
	local40
	local41
	[local42 11] = [1 2 1 2 3 1 2 1 2 3 1]
	[local53 11] = [6 5 6 5 5 6 5 6 5 5 6]
	[local64 11] = [64 59 64 59 61 64 59 64 59 61 64]
	[local75 11] = [0 5 0 5 10 0 5 0 5 10]
	[local86 11] = [50 15 50 15 15 50 15 50 15 15 50]
	[local97 11] = [50 30 50 30 30 50 30 50 30 30 50]
	[local108 11] = [1 2 1 3 4 1 2 1 3 4 5]
	local119
	[local120 9] = [1002 35 101 4 11 29 20 28 28]
	[local129 15] = [0 920 0 921 0 0 1000 233 21 4 11 24 19 23 30]
)
(procedure (localproc_2d75)
	(= global103 0)
	(User canInput: 1)
	(theIconBar enable:)
	(theIconBar disable: 0 1 2 3)
	(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
	(= isHandsOff 0)
)

(instance battle of KQ5Room
	(properties
		picture 65
	)
	
	(method (init)
		(= gMordObj mordObj)
		(= gNewActorCloner (actorCloner new:))
		(= gNewActorCloner_2 (actorCloner new:))
		(= gNewActorCloner_3 (actorCloner new:))
		(= gNewActorCloner_4 (actorCloner new:))
		(= gNewActorCloner_5 (actorCloner new:))
		((ScriptID 758) init:)
		(super init:)
		(LoadMany 128 715 714 548)
		(self setFeatures: room setScript: mordOneScript)
		(HandsOff)
		(ego
			normal: 0
			view: 715
			setLoop: 4
			cel: 0
			illegalBits: 0
			posn: 250 74
			init:
		)
		(if (IsObject (ego head?)) ((ego head?) dispose:))
		(if (or (not (Btst 60)) (not (ego has: 28)))
			(Bset 55)
		)
		(if (== (theGame detailLevel:) 3)
			(coals init: setCycle: RandCycle)
		else
			(coals init: addToPic:)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
		)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(DisposeScript 941)
		(DisposeScript 604)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance mordOneScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and register (< ((ScriptID 604 5) x?) 296))
			(self cue:)
			(= register 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsSOUND 892)
				(theMusic number: 840 loop: -1 playBed:)
				(= cycles 1)
			)
			(1
				(if
					(and
						(not global400)
						(not (== howFast 0))
						(< (theMusic prevSignal?) 30)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(2
				(gMordObj init: setCycle: End self)
			)
			(3
				(proc762_1 @local120 917 self)
			)
			(4
				(cls)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 222)
				(= seconds 1)
			)
			(5
				(gNewActorCloner
					view: 548
					posn: 155 30 0
					init:
					cycleSpeed: 4
				)
				(gNewActorCloner setCycle: MouthSync 918)
				(proc0_29 918 self)
			)
			(6
				(gNewActorCloner setCycle: 0 setCel: 0)
				(= seconds 4)
			)
			(7
				(gNewActorCloner view: 712 z: 1000)
				(cls)
				(cast eachElementDo: #show)
				(curRoom drawPic: 65)
				(UnLoad 128 548)
				(= seconds 3)
			)
			(8
				(if (OneOf ((inventory at: 35) owner?) ego 66)
					(self setScript: (ScriptID 604 7) self)
				else
					(= cycles 1)
				)
			)
			(9
				(if (not (Btst 55)) (= register 1) else (= cycles 1))
			)
			(10
				(gMordObj
					view: 715
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(11
				(gNewActorCloner
					posn: (+ (gMordObj x?) 17) (- (gMordObj y?) 39) 0
					view: 715
					setLoop: 3
					cel: 0
					cycleSpeed: 0
					init:
					setCycle: End self
				)
				(theAudio number: 8138 loop: 1 play:)
			)
			(12
				(gMordObj
					view: 714
					setLoop: 3
					cel: 0
					cycleSpeed: 0
					setCycle: End
				)
				(if (Btst 55)
					(= global103 0)
					(ego cycleSpeed: 1 setCycle: End self)
				else
					((ScriptID 604 5)
						setLoop: 0
						setCel: 0
						setCycle: End self
					)
					(theAudio number: 8897 loop: 1 play:)
				)
			)
			(13
				(if (Btst 55)
					(= global103 0)
					(= deathMessage 318)
					(EgoDead)
				else
					((ScriptID 604 5)
						setMotion: 0
						setLoop: 1
						setCycle: Fwd
						cycleSpeed: 1
						setScript: owlScript self
					)
				)
			)
			(14
				((ScriptID 604 5) setLoop: 2 cel: 0 setCycle: End self)
				(theAudio number: 8892 loop: 1 play:)
			)
			(15
				((ScriptID 604 5) stopUpd:)
				(gMordObj view: 715 setLoop: 0 cel: 0 setCycle: End self)
				(theMusic2 number: 125 loop: 1 priority: 15 play:)
			)
			(16 (= seconds 4))
			(17
				(proc762_1 @local120 919)
				(= cycles 1)
			)
			(18
				(cls)
				(gMordObj setLoop: 1 cel: 0 setCycle: End self)
			)
			(19 (= seconds 3))
			(20
				(proc762_0 @local120 @local120 @local129 self)
			)
			(21
				(UnLoad 128 714)
				(= cycles 1)
			)
			(22
				(cls)
				(curRoom setScript: stingScript)
			)
		)
	)
)

(instance owlScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 604 5)
					posn:
						(- ((ScriptID 604 5) x?) [local15 register])
						(+ ((ScriptID 604 5) y?) [local15 (++ register)])
				)
				(= cycles 2)
			)
			(1
				(if (== [local15 (++ register)] -32768)
					(self dispose:)
				else
					(if (== register 20) (client setPri: -1))
					(self init:)
				)
			)
		)
	)
)

(instance stingScript of Script
	(properties)
	
	(method (doit)
		(if (== state 5)
			(if local40 (= seconds 0))
			(if local41
				(Load rsSOUND 842)
				(= local41 0)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad 128 715)
				(LoadMany 128 722 725 724)
				(theMusic number: 841 loop: -1 playBed:)
				(= cycles 1)
			)
			(1 (= cycles 1))
			(2
				(gMordObj
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					setPri: 10
					view: 724
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(3
				(gMordObj
					view: 725
					setLoop: 1
					posn: (+ (gMordObj x?) 27) (- (gMordObj y?) 31)
					setStep: 6 6
					setCycle: Fwd
				)
				(= seconds 2)
			)
			(4
				(gMordObj
					setMotion: MoveTo (- (ego x?) 55) (- (ego y?) 31) self
				)
			)
			(5
				(localproc_2d75)
				(theGame setCursor: normalCursor yourself:)
				(= seconds 10)
			)
			(6
				(HandsOff)
				(if (== local40 4)
					(theMusic number: 842 loop: -1 playBed:)
					(= score (+ score 4))
					(= cycles 1)
				else
					(self setScript: (ScriptID 604 0))
				)
			)
			(7
				(ego
					view: 722
					setLoop: 2
					cycleSpeed: 2
					cel: 0
					posn: (- (ego x?) 23) (+ (ego y?) 1)
					setCycle: End self
				)
			)
			(8
				(ego setLoop: 0 cel: 1)
				(gNewActorCloner
					view: 722
					setLoop: 1
					cycleSpeed: 2
					illegalBits: 0
					signal: (| (gNewActorCloner signal?) $6000)
					posn: (ego x?) (ego y?) 0
					setCycle: End self
				)
				(gNewActorCloner_2
					view: 722
					setLoop: 3
					cycleSpeed: 2
					illegalBits: 0
					signal: (| (gNewActorCloner_2 signal?) $6000)
					posn: (+ (ego x?) 38) (ego y?) 0
					init:
					setCycle: Fwd
				)
				(theAudio number: 7064 loop: 1 play:)
			)
			(9
				(gNewActorCloner setCycle: Beg self)
			)
			(10
				(ego setLoop: 2 cel: 0 setCycle: End self)
				(gNewActorCloner z: 1000)
				(gNewActorCloner_2 z: 1000)
			)
			(11
				(ego setLoop: 0 cel: 1 setCycle: 0)
				(gNewActorCloner z: 0)
				(gNewActorCloner_2 z: 0)
				(proc0_29 922 0 1)
				(= seconds 4)
			)
			(12
				(theAudio number: 7064 loop: 1 play:)
				(gNewActorCloner setCycle: End self)
			)
			(13
				(gNewActorCloner setCycle: Beg self)
			)
			(14
				(ego setLoop: 2 cel: 0 setCycle: End self)
				(gNewActorCloner z: 1000)
				(gNewActorCloner_2 z: 1000)
			)
			(15
				(ego setLoop: 0 cel: 1 setCycle: 0)
				(gNewActorCloner z: 0)
				(gNewActorCloner_2 z: 0)
				(= seconds 2)
			)
			(16
				(cls)
				(gMordObj
					view: 729
					setCycle: 0
					cel: 1
					setPri: 13
					moveSpeed: 2
					setStep: 20 20
					setMotion: MoveTo 205 43 self
				)
			)
			(17
				(gMordObj cel: 2 setMotion: MoveTo 238 56 self)
			)
			(18
				(gMordObj cel: 3 setMotion: MoveTo 280 82 self)
			)
			(19
				(gMordObj cel: 4 setMotion: MoveTo 295 104 self)
			)
			(20
				(gMordObj cel: 5 setMotion: MoveTo 285 148 self)
			)
			(21
				(gMordObj cel: 6 setMotion: MoveTo 224 165 self)
			)
			(22
				(gMordObj cel: 6 setMotion: MoveTo 162 162 self)
			)
			(23
				(gMordObj cel: 6 setMotion: MoveTo 125 135 self)
			)
			(24
				(gMordObj cel: 6 setMotion: MoveTo 92 120 self)
			)
			(25
				(gMordObj cel: 7 setMotion: MoveTo 67 134 self)
			)
			(26
				(gMordObj cel: 8 setMotion: MoveTo 82 150 self)
			)
			(27
				(gMordObj cel: 9 setMotion: MoveTo 124 148 self)
			)
			(28 (= seconds 2))
			(29
				(gNewActorCloner z: 1000)
				(gNewActorCloner_2 z: 1000)
				(ego setLoop: 4 cel: 0 setCycle: CT 9 1 self)
			)
			(30
				(ego setPri: 5 setCycle: End self)
			)
			(31
				(ego
					setLoop: 5
					cel: 0
					cycleSpeed: 4
					setCycle: End
					setStep: 10 10
					setMotion: MoveTo (- (ego x?) 35) (+ (ego y?) 90) self
				)
			)
			(32
				(ego cycleSpeed: 0 setLoop: 6 cel: 0 setCycle: End self)
			)
			(33
				(ego setLoop: 8 cel: 1)
				(gNewActorCloner
					setLoop: 9
					cel: 0
					posn: (- (ego x?) 17) (- (ego y?) 4) 0
					setScript: (ScriptID 604 6)
				)
				(gNewActorCloner_2
					setLoop: 10
					posn: (+ (ego x?) 21) (ego y?) 0
					setPri: -1
					setCycle: Fwd
				)
				(gMordObj
					view: 725
					setLoop: 0
					setPri: 13
					setStep: 3 3
					setCycle: Fwd
					setMotion: MoveTo (- (gMordObj x?) 60) (- (gMordObj y?) 15) self
				)
			)
			(34
				(curRoom setScript: dragonScript)
				(= local40 0)
			)
		)
	)
)

(instance dragonScript of Script
	(properties)
	
	(method (doit)
		(if (== state 5)
			(if local40 (= seconds 0))
			(if local41
				(Load rsSOUND 844)
				(= local41 0)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 128 728 730)
				(theMusic number: 843 loop: -1 playBed:)
				(= cycles 1)
			)
			(1
				(if
					(and
						(not global400)
						(not (== howFast 0))
						(< (theMusic prevSignal?) 10)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(2
				(gMordObj
					yStep: 10
					setMotion: MoveTo (gMordObj x?) (+ (gMordObj y?) 20) self
				)
			)
			(3
				(gMordObj
					view: 724
					setLoop: 1
					cel: 0
					yStep: 2
					setMotion: MoveTo (gMordObj x?) (+ (gMordObj y?) 30)
					setCycle: End self
				)
			)
			(4
				(UnLoad 128 724)
				(gMordObj
					view: 728
					setLoop: 0
					setPri: 13
					stopUpd:
					setMotion: 0
				)
				(gNewActorCloner_3
					view: 728
					setLoop: 1
					cel: 0
					ignoreActors:
					illegalBits: 0
					setPri: 14
					posn: (+ (gMordObj x?) 15) (- (gMordObj y?) 47) 0
					init:
				)
				(gNewActorCloner_4
					view: 728
					setLoop: 4
					cel: 0
					ignoreActors:
					illegalBits: 0
					setPri: 15
					posn: (+ (gMordObj x?) 14) (- (gMordObj y?) 37) 0
					init:
					cycleSpeed: 3
					setScript: armScript
				)
				(= cycles 1)
			)
			(5
				(UnLoad 128 725)
				(UnLoad 128 722)
				(localproc_2d75)
				(= seconds 10)
			)
			(6
				(HandsOff)
				(if (== local40 2)
					(= score (+ score 4))
					(= cycles 1)
				else
					(self setScript: (ScriptID 604 1))
				)
			)
			(7
				(theMusic number: 844 loop: -1 playBed:)
				(LoadMany 128 738 739)
				(= cycles 5)
			)
			(8
				(gNewActorCloner setScript: 0)
				(gNewActorCloner_4 setScript: 0)
				(ego
					view: 738
					setLoop: 0
					cel: 0
					setStep: 10 10
					setCycle: End self
				)
			)
			(9 (= seconds 1))
			(10
				(gNewActorCloner_3
					setLoop: [local42 register]
					cel: 0
					setCycle: End self
				)
			)
			(11
				(gNewActorCloner_5
					view: 728
					setLoop: [local53 register]
					illegalBits: 0
					ignoreActors:
					posn:
						(+ (gNewActorCloner_3 x?) [local64 register])
						(+ (gNewActorCloner_3 y?) [local75 register])
					setStep: 9 9
					setPri: 13
					init:
				)
				(RedrawCast)
				(gNewActorCloner_5
					setMotion:
						MoveTo
						(+ (gNewActorCloner_5 x?) [local86 register])
						(+ (gNewActorCloner_5 y?) [local97 register])
						self
				)
				(theAudio number: 8200 loop: 1 play:)
				(gNewActorCloner_3 setCycle: Beg)
				(ego setScript: bunnyScript 0 [local108 register])
			)
			(12
				(gNewActorCloner_5 setLoop: 7 cel: 0 setCycle: End self)
			)
			(13
				(cond 
					((== register 10) (= cycles 10))
					((ego script?) (-- state) (= cycles 1))
					(else (++ register) (= start 9) (self init:))
				)
			)
			(14
				(gNewActorCloner_3 view: 730 setLoop: 1)
				(gNewActorCloner
					view: 730
					setLoop: 2
					cycleSpeed: 3
					posn: (+ (gNewActorCloner_3 x?) 21) (- (gNewActorCloner_3 y?) 16) 0
					setPri: (- (gNewActorCloner_3 priority?) 1)
				)
				(gNewActorCloner setCycle: MouthSync 923)
				(proc0_29 923 0 1)
				(= seconds 10)
			)
			(15
				(gNewActorCloner setCycle: 0)
				(cls)
				(= local40 0)
				(curRoom setScript: snakeScript)
			)
		)
	)
)

(instance bunnyScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 egoX egoY)
		(switch (= state newState)
			(0
				(switch register
					(1
						(= temp0 739)
						(= temp1 0)
						(= temp2 (- (gNewActorCloner_5 priority?) 1))
					)
					(2
						(= temp0 738)
						(= temp1 3)
						(= temp2 (+ (gNewActorCloner_5 priority?) 1))
					)
					(3
						(= temp0 738)
						(= temp1 2)
						(= temp2 (- (gNewActorCloner_5 priority?) 1))
					)
					(4
						(= temp0 738)
						(= temp1 3)
						(= temp2 (+ (gNewActorCloner_5 priority?) 1))
					)
					(5
						(= temp0 739)
						(= temp1 0)
						(= temp2 (- (gNewActorCloner_5 priority?) 1))
					)
				)
				(ego
					view: temp0
					setLoop: temp1
					cel: 0
					setPri: temp2
					setCycle: End self
				)
			)
			(1
				(switch register
					(1
						(= temp0 739)
						(= temp1 2)
						(= egoX (- (ego x?) 24))
						(= egoY (- (ego y?) 10))
					)
					(2
						(= temp0 738)
						(= temp1 5)
						(= egoX (+ (ego x?) 14))
						(= egoY (- (ego y?) 6))
					)
					(3
						(= temp0 738)
						(= temp1 4)
						(= egoX (- (ego x?) 15))
						(= egoY (- (ego y?) 6))
					)
					(4
						(= temp0 738)
						(= temp1 5)
						(= egoX (+ (ego x?) 14))
						(= egoY (- (ego y?) 6))
					)
					(5
						(= temp0 739)
						(= temp1 2)
						(= egoX (- (ego x?) 24))
						(= egoY (- (ego y?) 10))
					)
				)
				(ego
					view: temp0
					setLoop: temp1
					cel: 0
					posn: egoX egoY
					setCycle: End self
				)
			)
			(2
				(switch register
					(1
						(= egoX (- (ego x?) 9))
						(= egoY (- (ego y?) 3))
					)
					(2
						(= egoX (+ (ego x?) 10))
						(= egoY (+ (ego y?) 6))
					)
					(3
						(= egoX (ego x?))
						(= egoY (ego y?))
					)
					(4
						(= egoX (+ (ego x?) 41))
						(= egoY (+ (ego y?) 2))
					)
					(5
						(= egoX (ego x?))
						(= egoY (ego y?))
					)
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(3
				(switch register
					(1
						(= temp0 739)
						(= temp1 4)
						(= egoX (- (ego x?) 7))
						(= egoY (+ (ego y?) 3))
					)
					(2
						(= temp0 738)
						(= temp1 7)
						(= egoX (+ (ego x?) 16))
						(= egoY (+ (ego y?) 10))
					)
					(3
						(= temp0 738)
						(= temp1 6)
						(= egoX (- (ego x?) 9))
						(= egoY (+ (ego y?) 10))
					)
					(4
						(= temp0 738)
						(= temp1 7)
						(= egoX (+ (ego x?) 9))
						(= egoY (+ (ego y?) 10))
					)
					(5
						(= temp0 739)
						(= temp1 4)
						(= egoX (- (ego x?) 13))
						(= egoY (- (ego y?) 9))
					)
				)
				(ego
					view: temp0
					setLoop: temp1
					cel: 0
					posn: egoX egoY
					setCycle: End self
				)
			)
			(4
				(if (== register 5)
					(ego
						view: 739
						setLoop: 6
						posn: 145 136
						cycleSpeed: 1
						setCycle: Fwd
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance armScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1 (= seconds (Random 1 5)))
			(2 (self init:))
		)
	)
)

(instance snakeScript of Script
	(properties)
	
	(method (doit)
		(if (== state 8)
			(if local40 (= seconds 0))
			(if local41
				(Load rsSOUND 846)
				(= local41 0)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 128 730 732)
				(theMusic number: 845 loop: -1 playBed:)
				(= cycles 1)
			)
			(1
				(gNewActorCloner_3 view: 712 z: 1000)
				(gNewActorCloner_4 view: 712 z: 1000)
				(gNewActorCloner_5 view: 712 z: 1000)
				(gNewActorCloner view: 712 z: 1000)
				(gMordObj view: 730 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(UnLoad 128 728)
				(UnLoad 128 730)
				(= seconds 2)
			)
			(3
				(gMordObj
					view: 732
					setLoop: 6
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(4 (= seconds 2))
			(5
				(gMordObj
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) 26) (+ (ego y?) 8) self
				)
			)
			(6
				(gMordObj setLoop: 8 cel: 0 setCycle: End self)
			)
			(7
				(gMordObj setLoop: 14 cycleSpeed: 3 setCycle: Fwd)
				(proc0_29 924 0 1)
				(= seconds 5)
			)
			(8
				(cls)
				(localproc_2d75)
				(= seconds 10)
			)
			(9
				(HandsOff)
				(if (== local40 1)
					(= score (+ score 4))
					(= cycles 1)
				else
					(self setScript: (ScriptID 604 2))
				)
			)
			(10
				(theMusic number: 846 loop: -1 playBed:)
				(LoadMany 128 741)
				(= cycles 5)
			)
			(11
				(ego
					view: 741
					setLoop: 3
					cel: 0
					moveSpeed: 2
					setCycle: End self
				)
			)
			(12
				(ego
					setLoop: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (ego x?) 5) (ego y?) self
				)
			)
			(13
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
				(gMordObj setCycle: 0 setLoop: 12 cel: 1)
				(= cycles 3)
			)
			(14 (gMordObj cel: 0))
			(15
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(16
				(ego
					setLoop: 1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (gMordObj x?) 26) (- (gMordObj y?) 6) self
				)
			)
			(17
				(gMordObj z: 1000)
				(ego
					setLoop: 8
					cel: 0
					cycleSpeed: 2
					posn: (gMordObj x?) (gMordObj y?)
					setCycle: End self
				)
			)
			(18
				(gMordObj z: 0)
				(ego
					setLoop: 0
					cycleSpeed: 0
					posn: (+ (ego x?) 35) (- (ego y?) 2)
				)
				(RedrawCast)
				(ego
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (ego x?) 5) (ego y?) self
				)
			)
			(19
				(ego setMotion: MoveTo (+ (ego x?) 20) (ego y?) self)
				(gMordObj setLoop: 12 cel: 1)
				(= cycles 3)
			)
			(20 (gMordObj cel: 0))
			(21
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(22
				(ego
					setLoop: 1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (gMordObj x?) 26) (- (ego y?) 5) self
				)
			)
			(23
				(gMordObj z: 1000)
				(ego
					setLoop: 10
					cycleSpeed: 2
					cel: 0
					posn: (gMordObj x?) (gMordObj y?)
					setCycle: End self
				)
			)
			(24
				(gMordObj z: 0)
				(ego
					setLoop: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					posn: (+ (ego x?) 31) (- (ego y?) 5)
				)
				(RedrawCast)
				(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
			)
			(25
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(26
				(ego
					setLoop: 1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (gMordObj x?) 26) (- (gMordObj y?) 6) self
				)
			)
			(27
				(gMordObj z: 1000)
				(ego
					setLoop: 8
					cycleSpeed: 2
					cel: 0
					posn: (gMordObj x?) (gMordObj y?)
					setCycle: End self
				)
			)
			(28
				(gMordObj z: 0)
				(ego
					setLoop: 0
					cycleSpeed: 0
					posn: (+ (ego x?) 35) (- (ego y?) 2)
				)
				(RedrawCast)
				(ego
					setLoop: 1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (+ (gMordObj x?) 45) (- (gMordObj y?) 4) self
				)
			)
			(29
				(ego
					view: 741
					setLoop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(30
				(gMordObj setLoop: 14 cycleSpeed: 1 setCycle: Fwd)
				(proc0_29 925 0 1)
				(= seconds 4)
			)
			(31
				(ego cel: 0 setCycle: End self)
				(= seconds 4)
			)
			(32
				(theMusic number: 847 loop: -1 playBed:)
				(cls)
				(ego setLoop: 12 cycleSpeed: 0 cel: 0)
				(= local40 0)
				(curRoom setScript: fireScript)
			)
		)
	)
)

(instance fireScript of Script
	(properties)
	
	(method (doit)
		(if (== state 4)
			(if local40 (= seconds 0))
			(if local41
				(Load rsSOUND 849)
				(= local41 0)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad 128 732)
				(LoadMany 128 734 719)
				(gMordObj
					view: 734
					setLoop: 0
					cel: 0
					setPri: (- (ego priority?) 1)
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(1
				(gMordObj setLoop: 1 cel: 0 setCycle: End self)
				(gNewActorCloner_2
					view: 734
					setLoop: 2
					cel: 0
					cycleSpeed: 2
					posn: (gMordObj x?) (gMordObj y?) 0
					setPri: (+ (ego priority?) 1)
					setScript: 0
					setCycle: End self
				)
			)
			(2 0)
			(3
				(gMordObj
					setLoop: 3
					posn: (+ (gMordObj x?) 45) (- (gMordObj y?) 4)
					setCycle: Fwd
				)
				(gNewActorCloner_2
					setLoop: 4
					posn: (gMordObj x?) (gMordObj y?)
					setCycle: Fwd
				)
				(ego setLoop: 12 cel: 0 setCycle: End self)
			)
			(4
				(localproc_2d75)
				(= local119 1)
				(= seconds 10)
			)
			(5
				(HandsOff)
				(if (== local40 3)
					(= score (+ score 4))
					(= cycles 1)
				else
					(self setScript: (ScriptID 604 3))
				)
			)
			(6
				(theMusic number: 849 loop: -1 playBed:)
				(LoadMany 128 743 742)
				(= cycles 5)
			)
			(7
				(ego
					view: 742
					setLoop: 2
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(8
				(if
					(and
						(not global400)
						(not (== howFast 0))
						(< (theMusic prevSignal?) 10)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(9
				(gNewActorCloner_3
					view: 742
					setLoop: 3
					cel: 0
					posn: (+ (gMordObj x?) 2) (- (gMordObj y?) 56) 0
					init:
					cycleSpeed: 2
					setCycle: End self
				)
				(ego setLoop: 1 cel: 2 setCycle: Beg)
			)
			(10
				(if
					(and
						(not global400)
						(not (== howFast 0))
						(< (theMusic prevSignal?) 20)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(11
				(gNewActorCloner_3 setLoop: 4 setCycle: Fwd)
				(gNewActorCloner_5
					view: 742
					setLoop: 8
					posn: (- (gNewActorCloner_3 x?) 2) (+ (gNewActorCloner_3 y?) 7) 0
					setPri: (+ (gNewActorCloner_3 priority?) 1)
					cycleSpeed: 0
					setScript: 0
					setCycle: Fwd
				)
				(gNewActorCloner_4
					view: 742
					setLoop: 5
					cel: 0
					posn: (+ (gMordObj x?) 2) (- (gMordObj y?) 49) 0
					setPri: (+ (ego priority?) 1)
					init:
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(12
				(gNewActorCloner_4 setLoop: 6 setCycle: Fwd)
				(= seconds 2)
				(ego setLoop: 0 cel: 1 z: 0)
				(gNewActorCloner dispose:)
			)
			(13
				(gMordObj view: 743 setLoop: 0)
				(gNewActorCloner_2 view: 743 setLoop: 1)
				(= seconds 2)
			)
			(14
				(UnLoad 128 734)
				(gMordObj setLoop: 2)
				(gNewActorCloner_2 setLoop: 3)
				(= seconds 1)
			)
			(15
				(gMordObj setLoop: 4 setCycle: End self)
				(gNewActorCloner_2 setLoop: 5 setCycle: End self)
				(gNewActorCloner_4 setLoop: 7 cel: 0 setCycle: End self)
			)
			(16 0)
			(17 0)
			(18
				(theMusic number: 850 loop: 1 playBed: self)
				(gMordObj dispose:)
				(gNewActorCloner_2 dispose:)
				(gNewActorCloner_4 dispose:)
				(gNewActorCloner_5 dispose:)
				(gNewActorCloner_3 setLoop: 9 cel: 0 setCycle: End self)
			)
			(19)
			(20 (= seconds 2))
			(21
				(gNewActorCloner_3 dispose:)
				(ego cycleSpeed: 0)
				(if (== howFast 0)
					(curRoom newRoom: 612)
				else
					(curRoom newRoom: 670)
				)
			)
		)
	)
)

(instance transformScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego cycleSpeed: 3)
				(switch global387
					(0 (= cycles 1))
					(4
						(ego view: 721 setLoop: 1 cel: 6 setCycle: Beg self)
						(theAudio stop:)
						(gNewActorCloner setScript: 0)
						(gNewActorCloner view: 712 z: 1000)
						(gNewActorCloner_2 view: 712 z: 1000)
					)
					(2
						(if (== local40 1)
							(ego view: 740 setLoop: 0 cel: 0 setCycle: End self)
							(= register 1)
						else
							(ego view: 740 setLoop: 2 cel: 7 setCycle: Beg self)
						)
					)
					(1
						(ego view: 740 setLoop: 1 cel: 7 setCycle: Beg self)
					)
				)
			)
			(1
				(if (< (ego y?) 100) (= temp0 1) else (= temp0 0))
				(switch local40
					(0
						(= global387 0)
						(= cycles 1)
					)
					(4
						(= global387 4)
						(if temp0
							(ego
								view: 721
								setLoop: 0
								cel: 0
								setPri: -1
								posn: (- (ego x?) 6) (- (ego y?) 7)
								setCycle: End self
							)
						else
							(ego view: 721 setLoop: 1 cel: 0 setCycle: End self)
						)
					)
					(2
						(= global387 2)
						(ego
							view: (if temp0 718 else 740)
							setLoop: (if temp0 0 else 2)
							cel: 0
							setCycle: End self
						)
					)
					(1
						(= global387 1)
						(if register
							(= cycles 1)
						else
							(ego
								view: (if temp0 718 else 740)
								setLoop: (if temp0 3 else 1)
								cel: 0
								setCycle: End self
							)
						)
					)
					(3
						(= global387 3)
						(ego
							view: (if temp0 718 else 742)
							setLoop: (if temp0 5 else 1)
							cel: 0
						)
						(if temp0
							(ego setCycle: CT 5 1 self)
						else
							(ego z: 19 setCycle: End self)
							(gNewActorCloner
								view: 742
								setLoop: 0
								setCycle: 0
								setCel: 0
								posn: (ego x?) (ego y?) 0
								setPri: (ego priority?)
								setScript: 0
								init:
							)
						)
					)
				)
			)
			(2
				(if (and (== local40 3) (not local119))
					(actorSix z: 1000 init: setScript: rainKludgeScript)
				else
					(= local41 1)
				)
				(ego cycleSpeed: 0)
				(= register 0)
				(client setScript: 0)
			)
		)
	)
)

(instance rainKludgeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 742
					setLoop: 3
					ignoreActors: 1
					posn: (ego x?) (- (ego y?) 40) 0
					setPri: 9
					cel: 0
					setCycle: End self
				)
			)
			(1
				(client setLoop: 4 setCycle: Fwd)
				(= cycles 10)
			)
			(2 (= local41 1))
		)
	)
)

(instance coals of Prop
	(properties
		x 281
		y 152
		view 712
		loop 1
		priority 12
		signal $4010
		cycleSpeed 10
	)
)

(instance mordObj of Actor
	(properties
		x 70
		y 163
		view 714
		signal $6000
	)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance getWand of Path
	(properties)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance actorSix of Actor
	(properties)
)

(instance actorCloner of Actor
	(properties)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event &tmp inventoryFirst temp1 theInventoryFirst)
		(if
			(or
				isHandsOff
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(HandsOn)
			(switch (event message?)
				(JOY_DOWNRIGHT
					(theIconBar disable: 0 1 2 3)
					(if
						(OneOf
							(inventory indexOf: (theIconBar curInvIcon?))
							28
							39
							41
							38
							40
						)
						(if (not (Btst 21))
							(event claimed: 0)
						else
							(if
							(== (inventory indexOf: (theIconBar curInvIcon?)) 28)
								(= score (+ score 4))
								(= inventoryFirst (inventory first:))
								(while inventoryFirst
									(= theInventoryFirst (inventory next: inventoryFirst))
									(= temp1 (NodeValue inventoryFirst))
									(cond 
										((not (temp1 isMemberOf: IconI)) (temp1 moveTo: 1))
										(
											(or
												(== (temp1 message?) 2)
												(== (temp1 message?) 6)
												(== (temp1 message?) 3)
											)
											(inventory disable: temp1)
										)
									)
									(= inventoryFirst theInventoryFirst)
								)
								(inventory heading: {Select spell to cast} showScore: 0)
								(ego get: 38)
								(ego get: 39)
								(ego get: 40)
								(ego get: 41)
							)
							(theIconBar enable: 5)
							(repeat
								(if (== (event type?) evMOUSEBUTTON)
									(theGame setCursor: 999 1)
									(proc0_8 0 4 3840 0)
								else
									(proc0_8 0 4 9 0)
								)
								(if
									(>
										(= local40
											(+
												(- (inventory indexOf: (theIconBar curInvIcon?)) 38)
												1
											)
										)
										0
									)
									(break)
								)
							)
							(theIconBar disable: 5)
							(theIconBar disable: 0 1 2 3)
							(HandsOff)
							(ego setScript: transformScript)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)
