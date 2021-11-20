;;; Sierra Script 1.0 - (do not remove this comment)
(script# 730)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use GloryTalker)
(use Scaler)
(use Feature)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm730 0
	eranaTalker 1
	avis 2
	katrina 3
	pSpark 4
	fireBall 5
	midBlast 6
	leftBlast 7
	rightBlast 8
	crystal 9
	sTimeItOut 10
)

(local
	newFireBall
	local1
	local2
	local3
	local4
	local5
	gTheObj_2CycleSpeed
	local7
	local8
	local9
	local10
	gTheObj_2View
	gTheObj_2Loop
)
(instance rm730 of GloryRm
	(properties
		picture 730
	)
	
	(method (init)
		(Bclr 6)
		(Bclr 373)
		(super init:)
		(theMusic number: 203 loop: -1 play:)
		(crystal init: setPri: 64)
		(katrina init:)
		(avis init: signal: (| (avis signal?) $0001))
		(midBlast hide:)
		(leftBlast hide:)
		(rightBlast hide:)
		(ego
			view: 7
			cel: 0
			x: 170
			y: 140
			init:
			setScaler: Scaler 92 89 157 74
		)
		(theGame handsOn:)
		(theIconBar disable: 0)
		(fNode1 init:)
		(fNode2 init:)
		(fNode3 init:)
		(fNode4 init:)
		(fNode5 init:)
		(fNode6 init:)
		(fNode7 init:)
		(adavisTeller init: avis 730 9 160 7)
		(heroTeller init: ego 730 9 128 7)
		(self setScript: (ScriptID 731 0))
		(theGame save: 1)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(Btst 360)
				(not (curRoom script?))
				(not local5)
				(!= heroType 1)
			)
			(= local5 1)
			(curRoom setScript: sDoTheStaff)
		)
	)
	
	(method (dispose)
		(if script (script dispose:))
		(Bset 373)
		(DisposeScript 731)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(10
					(messager say: 0 159 0 0)
					(return 1)
				)
				(60
					(messager say: 0 60 0 0)
					(return 1)
				)
				(85
					(ego trySkill: 25)
					(if local2
						(messager say: 0 85 0 0)
					else
						(messager say: 0 85 36)
					)
					(return 1)
				)
				(56
					(if local2
						(messager say: 0 56 0)
					else
						(messager say: 0 56 36)
					)
					(return 1)
				)
				(83
					(ego trySkill: 23)
					(= local1 2)
					(if local2
						(messager say: 0 83 0)
					else
						(messager say: 0 83 36)
					)
					(return 1)
				)
				(81
					(ego trySkill: 21)
					(if local2
						(messager say: 0 81 0 0)
					else
						(messager say: 0 81 36)
					)
					(return 1)
				)
				(86
					(ego trySkill: 26)
					(= local1 4)
					(self setScript: (ScriptID 32) self 86)
				)
				(88
					(ego trySkill: 28)
					(= local1 5)
					(self setScript: (ScriptID 32) self 88)
				)
				(79
					(ego trySkill: 34)
					(= local1 6)
					(self setScript: (ScriptID 32) self 79)
				)
				(95
					(= local1 7)
					(if local2
						(messager say: 0 95 0 0)
					else
						(messager say: 0 95 36)
					)
					(return 1)
				)
				(93
					(ego trySkill: 33)
					(= local1 8)
					(self setScript: (ScriptID 32) self 93)
				)
				(7
					(messager say: 0 7 0)
					(return 1)
				)
				(33
					(ego trySkill: 10)
					(cond 
						(local2 (super doVerb: theVerb))
						(local4 (messager say: 5 33 39))
						(else (self setScript: sBurnUpGrapnel))
					)
				)
				(92
					(if (== (ego view?) 20)
						(messager say: 0 92 38)
						(return 1)
					else
						(= local1 9)
						(self setScript: (ScriptID 46) self)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(switch local1
			(9
				(if local2
					(curRoom setScript: sDoTheEndGame)
				else
					(curRoom setScript: sSummon)
				)
			)
			(4 (messager say: 0 86 0 0))
			(5 (messager say: 0 88 0 0))
			(6 (messager say: 0 79 0 0))
			(8 (messager say: 0 93 0 0))
		)
		(= local1 0)
	)
)

(instance sSummon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= [egoStats 17] (ego maxHealth:))
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 19] (ego maxMana:))
				(if (or local4 local8)
					(self cue:)
				else
					(= local8 1)
					(messager say: 5 6 30 0 self)
				)
			)
			(1
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sMessages of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				(if local4
					(messager say: 6 6 9 0 self)
				else
					(= ticks 1)
				)
			)
			(2
				(if (== local1 0)
					(messager say: 5 6 5 0 self)
				else
					(messager say: 5 6 10 0 self)
				)
			)
			(3
				(self setScript: sCastASpell)
			)
		)
	)
)

(instance sCastASpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(1
				((= newFireBall (fireBall new:))
					view: 747
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(2
				(newFireBall setCycle: End self)
			)
			(3
				(newFireBall dispose:)
				(cond 
					(
						(and
							(> (ego view?) 17)
							(< (ego view?) 21)
							(< (ego view?) 21)
						)
						(ego takeDamage: 23)
					)
					((> resistTimer 0) (ego takeDamage: (- 50 (/ (* [egoStats 26] 15) 100))))
					(else (ego takeDamage: 50))
				)
				(if (== [egoStats 17] 0)
					(self setScript: sEgoDies)
				else
					(self cue:)
				)
			)
			(4
				(DailyMsg 5 6 13 sCastASpell 730)
			)
			(5
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sEgoDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local10 1)
				(theMusic number: 106 loop: -1 play:)
				(avis setLoop: 0 1 setCel: 0)
				(ego
					view: 743
					setLoop: 0 1
					setCel: 0
					x: (+ (ego x?) 2)
					y: (- (ego y?) 1)
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1
				(ego
					posn: 146 162
					setCel: 5
					moveSpeed: 0
					setMotion: MoveTo 146 162 self
				)
			)
			(2 (curRoom newRoom: 180))
		)
	)
)

(instance sAdavisDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theGame handsOff:)
				(= local2 1)
				(heroTeller dispose:)
				(avis setScript: 0)
				(avis view: 748 setLoop: 0 1 setCel: 0 posn: 91 141)
				(self cue:)
			)
			(2
				(avis setCel: 0 setCycle: CT 2 1 self)
			)
			(3
				(avis setCel: 3 posn: 100 139)
				(self cue:)
			)
			(4
				(avis setCel: 4 posn: 105 139 setCycle: CT 8 1 self)
			)
			(5
				(avis
					setLoop: 1 1
					setCel: 0
					posn: 106 137
					setCycle: End self
				)
			)
			(6
				(avis dispose:)
				(theMusic number: 107 loop: 1 play: self)
			)
			(7
				(if (Btst 360) (messager say: 6 6 16 0))
				(theMusic number: 440 loop: -1 play:)
				(crystal
					signal: (| (crystal signal?) $0001)
					setCycle: Fwd
				)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sBurnUpGrapnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2View (ego view?))
				(= gTheObj_2Loop (ego loop?))
				(ego view: 8 setLoop: 1 1 setCel: 0 setCycle: 0)
				(= ticks 30)
			)
			(1
				(avis view: 677 setLoop: 0 1 cel: 0 setCycle: CT 5 1 self)
			)
			(2
				((= newFireBall (fireBall new:))
					view: 747
					setLoop: 1 1
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(3
				(newFireBall setCycle: End self)
			)
			(4
				(newFireBall dispose:)
				(ego
					use: 16
					normalize:
					setCycle: 0
					view: gTheObj_2View
					loop: gTheObj_2Loop
				)
				(= ticks 6)
			)
			(5
				(messager say: 6 6 11 0 self)
			)
			(6
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sTimeItOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch arcadeLevel
					(1 (= ticks 2400))
					(2 (= ticks 1800))
					(3 (= ticks 1200))
				)
			)
			(1
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(2
				((= newFireBall (fireBall new:))
					view: 747
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(3
				(newFireBall setCycle: End self)
			)
			(4
				(newFireBall dispose:)
				(self setScript: sEgoDies)
			)
		)
	)
)

(instance sUltimateJoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 9 128 20 0 self)
			)
			(1
				(messager say: 5 6 12 0 self)
			)
			(2
				(avis view: 733 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(3
				(avis setLoop: 1 1 setCycle: Fwd)
				(switch arcadeLevel
					(1 (= seconds 55))
					(2 (= seconds 45))
					(3 (= seconds 30))
				)
			)
			(4
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(5
				((= newFireBall (fireBall new:))
					view: 747
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(6
				(newFireBall setCycle: End self)
			)
			(7
				(newFireBall dispose:)
				(self setScript: sEgoDies)
			)
		)
	)
)

(instance sDoTheStaff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch heroType
					(0
						(aStaff init: setPri: 207 setCycle: End self)
					)
					(2
						(aStaff init: setPri: 207 setCycle: End self)
					)
					(3
						(aStaff init: setPri: 207 setCycle: End self)
					)
				)
			)
			(1
				(switch heroType
					(0
						(aStaff setLoop: 3 1 setCel: 0 setCycle: End self)
					)
					(2
						(aStaff setLoop: 2 1 setCel: 0 setCycle: End self)
					)
					(3
						(aStaff setLoop: 3 1 setCel: 0 setCycle: End self)
					)
				)
			)
			(2
				(switch heroType
					(0
						(aStaff
							setLoop: 4 1
							setCel: 1
							setCycle: 0
							setMotion: MoveTo 161 83 self
						)
					)
					(2
						(aStaff
							setLoop: 4 1
							setCel: 0
							setCycle: 0
							setMotion: MoveTo 161 76 self
						)
					)
					(3
						(aStaff
							setLoop: 4 1
							setCel: 1
							setCycle: 0
							setMotion: MoveTo 161 72 self
						)
					)
				)
			)
			(3
				(switch heroType
					(0
						(ego view: 10 setLoop: 0 1 setCel: 0 posn: 168 80)
						(= ticks 6)
					)
					(2
						(messager say: 6 6 26 0 self)
					)
					(3
						(ego view: 10 setLoop: 0 1 setCel: 0 posn: 168 80)
						(= ticks 6)
					)
				)
			)
			(4
				(if (not local4)
					(switch heroType
						(0
							(messager say: 5 6 14 0 self)
						)
						(2
							(messager say: 5 6 27 0 self)
						)
						(3
							(messager say: 5 6 14 0 self)
						)
					)
				else
					(self cue:)
				)
			)
			(5
				(theGame handsOn:)
				(aStaff hide: dispose:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sThrowYourWeapon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(avis setScript: 0)
				(if local4
					(ego view: 10 setLoop: 0 1 setCel: 0 setCycle: End self)
				else
					(self setScript: sBadThrow)
				)
			)
			(1
				(ego view: 5 setLoop: 5)
				(avis view: 733 setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(2
				(avis setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(3
				(theMusic number: 107 loop: 1 play: self)
			)
			(4
				(theMusic number: 440 loop: -1 play:)
				(avis
					view: 745
					setLoop: 3 1
					setCel: 5
					posn: 66 146
					setCycle: Beg self
				)
			)
			(5
				(avis
					setLoop: 1 1
					setCel: 2
					setCycle: 0
					ignoreActors: 1
					setMotion: MoveTo 149 72 self
				)
			)
			(6
				(messager say: 6 6 16 0 self)
			)
			(7
				(avis setLoop: 0 1 setCel: 6 setCycle: Beg self)
			)
			(8
				(avis hide: dispose:)
				(= local5 0)
				(= local2 1)
				(heroTeller dispose:)
				(crystal
					signal: (| (crystal signal?) $0001)
					setCycle: Fwd
				)
				(theGame handsOn:)
				(theIconBar disable: 0)
			)
		)
	)
)

(instance sBadThrow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 10 setLoop: 0 1 setCel: 0)
				(self cue:)
			)
			(1
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(2
				((= newFireBall (fireBall new:))
					view: 747
					setLoop: 1 1
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(3
				(newFireBall setCycle: End self)
			)
			(4
				(newFireBall dispose:)
				(aSpear
					init:
					setLoop: 1
					moveSpeed: 0
					setMotion: JumpTo 209 189 self
				)
				(ego view: 5 setLoop: 5)
			)
			(5
				(aSpear dispose:)
				(messager say: 5 6 15 0 self)
			)
			(6
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(7
				((= newFireBall (fireBall new:))
					view: 747
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 176 60 self
				)
				(avis setCycle: End)
			)
			(8
				(newFireBall setCycle: End self)
			)
			(9
				(newFireBall dispose:)
				(self setScript: sEgoDies)
			)
		)
	)
)

(instance sJumpToAdavis of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(avis setScript: 0)
				(if local4
					(ego trySkill: 15)
					(= local7 1)
					(= gTheObj_2CycleSpeed (ego cycleSpeed?))
					(= seconds 1)
				else
					(curRoom setScript: sJumpDeath)
				)
			)
			(1
				(avis
					view: 737
					setLoop: 1 1
					y: 145
					setCel: 0
					setCycle: End
				)
				(ego
					view: 30
					setLoop: 3 1
					setCel: 0
					setSpeed: 6
					setCycle: CT 6 1
					setMotion: JumpTo 125 120 self
				)
			)
			(2
				(avis
					view: 734
					setLoop: 0 1
					setCel: 0
					setPri: 180
					posn: 87 150
					setCycle: CT 2 1 self
				)
			)
			(3
				(avis hide:)
				(ego
					view: 734
					setLoop: 0 1
					setCel: 2
					setPri: 150
					setCycle: End self
					posn: 87 150
				)
			)
			(4
				(ego setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(5
				(ego setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(6
				(theMusic number: 107 loop: 1 play: self)
			)
			(7
				(theMusic number: 440 loop: -1 play:)
				(ego
					view: 5
					setLoop: 7 1
					setCel: 0
					posn: 81 137
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(avis
					view: 745
					setLoop: 2 1
					setCel: 5
					posn: 66 146
					show:
					setCycle: Beg self
				)
			)
			(8
				(avis
					setLoop: 1 1
					setCel: 2
					setCycle: 0
					ignoreActors: 1
					setMotion: MoveTo 61 119 self
				)
			)
			(9
				(messager say: 6 6 16 0 self)
			)
			(10
				(avis setLoop: 0 1 setCel: 6 setCycle: Beg self)
			)
			(11
				(Bclr 360)
				(= local5 0)
				(= local2 1)
				(heroTeller dispose:)
				(avis hide: dispose:)
				(crystal
					signal: (| (crystal signal?) $0001)
					setCycle: Fwd
				)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sJumpBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local7 0)
				(ego trySkill: 15)
				(ego
					view: 30
					setLoop: 2 1
					setCel: 0
					setSpeed: 6
					setCycle: CT 8 1
					setMotion: JumpTo 170 80 self
				)
			)
			(1
				(ego view: 5 setLoop: 2 1 setCel: 0)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sJumpDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(avis
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(1
				((= newFireBall (fireBall new:))
					view: 747
					x: 91
					y: 101
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 124 77 self
				)
				(avis setCycle: End)
			)
			(2
				(theMusic number: 106 loop: -1 play:)
				(ego view: 749 setLoop: 0 1 setCel: 0 posn: 126 84)
				(newFireBall setCycle: Fwd)
				(= ticks 12)
			)
			(3
				(ego setCel: 2)
				(= ticks 12)
			)
			(4
				(ego setCel: 1)
				(= ticks 12)
			)
			(5
				(ego setCel: 3)
				(= ticks 12)
			)
			(6
				(newFireBall dispose:)
				(ego dispose:)
				(= ticks 12)
			)
			(7 (curRoom newRoom: 180))
		)
	)
)

(instance sDoTheEndGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local7
					(self setScript: sJumpBack self)
				else
					(self cue:)
				)
			)
			(1
				(theGame handsOff:)
				(messager say: 1 157 16 0 self)
			)
			(2
				(ego
					view: 746
					setLoop: 0 1
					setCel: 0
					posn: 164 81
					setCycle: End self
				)
			)
			(3
				(theMusic number: 108 setLoop: -1 play:)
				(theMusic2 number: 971 loop: 1 play: self)
			)
			(4
				(crystal
					view: 735
					setLoop: 0 1
					setCel: 0
					posn: 124 91
					setCycle: End self
				)
			)
			(5
				(switch heroType
					(0
						(messager say: 8 6 33 0 self)
					)
					(1
						(messager say: 8 6 34 0 self)
					)
					(2
						(messager say: 8 6 33 0 self)
					)
					(3
						(messager say: 8 6 35 0 self)
					)
				)
			)
			(6
				(crystal setLoop: 1 1 setCel: 0 setCycle: Fwd)
				(= ticks 80)
			)
			(7
				(crystal setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(8
				(crystal setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(9
				(ego solvePuzzle: 464 50)
				(curRoom newRoom: 635)
			)
		)
	)
)

(instance avis of TargActor
	(properties
		noun 5
		x 73
		y 141
		view 737
	)
	
	(method (doVerb theVerb)
		(= projX ((User curEvent?) x?))
		(= projY ((User curEvent?) y?))
		(switch theVerb
			(33
				(ego trySkill: 10)
				(if local4
					(messager say: 6 6 29)
				else
					(curRoom setScript: sBurnUpGrapnel)
				)
			)
			(21
				(ego trySkill: 10)
				(= local1 0)
				(ego use: 6)
				(curRoom setScript: (ScriptID 32) 0 21)
			)
			(37
				(if (== (ego has: 5) 1)
					(messager say: 6 6 40)
				else
					(ego trySkill: 10)
					(ego use: 5)
					(= local1 0)
					(curRoom setScript: (ScriptID 32) 0 37)
				)
			)
			(86
				(ego trySkill: 26)
				(= local1 4)
				(curRoom setScript: (ScriptID 32) 0 86)
			)
			(88
				(ego trySkill: 28)
				(= local1 5)
				(curRoom setScript: (ScriptID 32) 0 88)
			)
			(93
				(ego trySkill: 33)
				(= local1 8)
				(curRoom setScript: (ScriptID 32) 0 93)
			)
			(79
				(ego trySkill: 34)
				(= local1 6)
				(curRoom setScript: (ScriptID 32) 0 79)
			)
			(157
				(if (OneOf heroType 0 3)
					(curRoom setScript: sThrowYourWeapon)
				else
					(messager say: 5 170 28)
				)
			)
			(10
				(if (== heroType 2)
					(if local5
						(curRoom setScript: sJumpToAdavis)
					else
						(messager say: 5 159 17)
					)
				else
					(messager say: 0 159 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(asm
			lal      local10
			not     
			bnt      code_1c23
			lal      local2
			not     
			bnt      code_1c23
			lal      local4
			bnt      code_1be4
			pushi    #script
			pushi    0
			lag      ego
			send     4
			not     
			bnt      code_1be4
			lsl      local1
			ldi      0
			ne?     
			bnt      code_1be4
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_1bd0
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_1bd0
code_1bd0:
			not     
			bnt      code_1be4
			pushi    #setScript
			pushi    1
			lofsa    sMessages
			push    
			lag      curRoom
			send     6
			jmp      code_1c23
code_1be4:
			lal      local4
			bnt      code_1c16
			pushi    #script
			pushi    0
			lag      ego
			send     4
			not     
			bnt      code_1c16
			pushi    #view
			pushi    1
			pushi    5
			pushi    254
			pushi    2
			pushi    5
			pushi    1
			lag      ego
			send     14
			pushi    #setScript
			pushi    1
			lofsa    sAdavisDies
			push    
			lag      ego
			send     6
			jmp      code_1c23
code_1c16:
			pushi    #setScript
			pushi    1
			lofsa    sMessages
			push    
			lag      curRoom
			send     6
code_1c23:
			ret     
		)
	)
)

(instance fireBall of Actor
	(properties
		priority 207
		fixPriority 1
		view 26
		loop 1
		yStep 15
		signal $4001
		xStep 15
		moveSpeed 0
	)
)

(instance katrina of Actor
	(properties
		noun 4
		x 231
		y 100
		priority 108
		fixPriority 1
		view 742
		loop 1
		signal $4001
	)
)

(instance aStaff of Actor
	(properties
		x 169
		y 38
		view 745
		signal $4001
		moveSpeed 0
	)
)

(instance aSpear of Actor
	(properties
		x 175
		y 94
		view 10
		loop 1
	)
)

(instance pSpark of Prop
	(properties
		view 732
		signal $4001
	)
)

(instance midBlast of Prop
	(properties
		noun 2
		x 172
		y 87
		view 730
		signal $4001
	)
)

(instance rightBlast of Prop
	(properties
		noun 2
		x 242
		y 101
		view 730
		loop 1
		signal $4001
	)
)

(instance leftBlast of Prop
	(properties
		noun 2
		x 94
		y 139
		view 730
		loop 2
		signal $4001
	)
)

(instance crystal of Prop
	(properties
		noun 1
		x 128
		y 53
		priority 97
		fixPriority 1
		view 731
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if local2
			(= projX ((User curEvent?) x?))
			(= projY ((User curEvent?) y?))
			(switch theVerb
				(10 (messager say: 1 159 0 0))
				(37
					(if (== (ego has: 5) 1)
						(messager say: 6 6 40)
					else
						(messager say: 1 37 0 0)
					)
				)
				(21 (messager say: 1 37 0 0))
				(86
					(self setScript: (ScriptID 32) crystal 86)
				)
				(88
					(self setScript: (ScriptID 32) crystal 88)
				)
				(79
					(self setScript: (ScriptID 32) crystal 79)
				)
				(33 (messager say: 1 33 0 0))
				(93
					(self setScript: (ScriptID 32) crystal 93)
				)
				(36 (messager say: 1 36 0 0))
				(157
					(curRoom setScript: sDoTheEndGame)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(messager say: 1 157 0 0)
		)
	)
	
	(method (cue)
		(messager say: 1 86 0 0)
	)
)

(instance fNode1 of Feature
	(properties
		noun 2
		nsLeft 7
		nsTop 53
		nsRight 76
		nsBottom 96
		sightAngle 180
		x 41
		y 74
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(4 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance fNode2 of Feature
	(properties
		noun 2
		nsLeft 47
		nsTop 136
		nsRight 109
		nsBottom 168
		sightAngle 180
		x 78
		y 136
	)
	
	(method (doVerb theVerb)
		(fNode1 doVerb: theVerb)
	)
)

(instance fNode3 of Feature
	(properties
		noun 2
		nsLeft 88
		nsTop 114
		nsRight 144
		nsBottom 155
		sightAngle 180
		x 116
		y 134
	)
	
	(method (doVerb theVerb)
		(fNode1 doVerb: theVerb)
	)
)

(instance fNode4 of Feature
	(properties
		noun 2
		nsLeft 142
		nsTop 71
		nsRight 202
		nsBottom 105
		sightAngle 180
		x 172
		y 88
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 10)
			(if (and local2 local7)
				(curRoom setScript: sJumpBack)
			else
				(messager say: 0 159 0 0)
			)
		else
			(fNode1 doVerb: theVerb)
		)
	)
)

(instance fNode5 of Feature
	(properties
		noun 2
		nsLeft 153
		nsTop 146
		nsRight 211
		nsBottom 189
		sightAngle 180
		x 182
		y 146
	)
	
	(method (doVerb theVerb)
		(fNode1 doVerb: theVerb)
	)
)

(instance fNode6 of Feature
	(properties
		noun 2
		nsLeft 208
		nsTop 97
		nsRight 266
		nsBottom 128
		sightAngle 180
		x 237
		y 112
	)
	
	(method (doVerb theVerb)
		(fNode1 doVerb: theVerb)
	)
)

(instance fNode7 of Feature
	(properties
		noun 2
		nsLeft 259
		nsTop 22
		nsRight 303
		nsBottom 61
		sightAngle 180
		x 281
		y 41
	)
	
	(method (doVerb theVerb)
		(fNode1 doVerb: theVerb)
	)
)

(instance adavisTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 74 0))
	)
)

(instance heroTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(ego view: 5 setLoop: 5 1)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 20)
			(self clean:)
			(= local4 1)
			(avis setScript: sUltimateJoke)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 20 (not local4))
	)
)

(instance eranaTalker of GloryTalker
	(properties
		x 0
		y 5
		talkWidth 150
		view 736
		textX 140
		textY 15
	)
	
	(method (init)
		(super init: eranaMouth 0 eranaEyes eranaFrame &rest)
		(eranaExtra init: cycleSpeed: 12 setCycle: Fwd)
		(star1 init: cycleSpeed: 12 setCycle: Fwd)
		(star2 init: cycleSpeed: 12 setCycle: Fwd)
		(star3 init: cycleSpeed: 12 setCycle: Fwd)
		(star4 init: cycleSpeed: 12 setCycle: Fwd)
		(star5 init: cycleSpeed: 12 setCycle: Fwd)
		(star6 init: cycleSpeed: 12 setCycle: Fwd)
		(star7 init: cycleSpeed: 12 setCycle: Fwd)
	)
	
	(method (dispose param1)
		(if (or (not argc) param1)
			(eranaExtra dispose:)
			(star1 dispose:)
			(star2 dispose:)
			(star3 dispose:)
			(star4 dispose:)
			(star5 dispose:)
			(star6 dispose:)
			(star7 dispose:)
		)
		(super dispose: param1)
	)
	
	(method (hide)
		(eranaExtra hide:)
		(star1 hide:)
		(star2 hide:)
		(star3 hide:)
		(star4 hide:)
		(star5 hide:)
		(star6 hide:)
		(star7 hide:)
		(super hide:)
	)
	
	(method (showAgain)
		(eranaExtra show:)
		(star1 show:)
		(star2 show:)
		(star3 show:)
		(star4 show:)
		(star5 show:)
		(star6 show:)
		(star7 show:)
		(super showAgain:)
	)
)

(instance eranaFrame of View
	(properties
		y 5
		view 736
	)
)

(instance eranaMouth of Prop
	(properties
		x 46
		y 70
		view 736
		loop 1
	)
)

(instance eranaEyes of Prop
	(properties
		x 46
		y 48
		view 736
		loop 2
	)
)

(instance eranaExtra of Prop
	(properties
		x 45
		y 103
		view 736
		loop 3
	)
)

(instance star1 of Prop
	(properties
		x 160
		y 100
		view 736
		loop 4
	)
)

(instance star2 of Prop
	(properties
		x 104
		y 9
		view 736
		loop 5
	)
)

(instance star3 of Prop
	(properties
		x 8
		y 8
		view 736
		loop 6
	)
)

(instance star4 of Prop
	(properties
		y 93
		view 736
		loop 7
	)
)

(instance star5 of Prop
	(properties
		x 145
		y 38
		view 736
		loop 7
	)
)

(instance star6 of Prop
	(properties
		x 16
		y 51
		view 736
		loop 7
	)
)

(instance star7 of Prop
	(properties
		x 133
		y 3
		view 736
		loop 7
	)
)
