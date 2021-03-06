;;; Sierra Script 1.0 - (do not remove this comment)
(script# rLeprechaunThrone)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use DPath)
(use DCIcon)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm77 0
)

(local
	local0
	oldSpeed
	local2
)
(instance rm77 of Room
	(properties
		picture pLeprechaunAGI
		north rCaveHole
	)
	
	(method (init)
		(LoadMany VIEW
			vLepKingSitting
			vLepKing
			vLepGirls
			vLepMan1
			vLepMan2
			vLepManWalk
			vLepGirl1Walk
			vLepGirl2Walk
			vLepHarpist
			vLepDrummer
			vLep1AGI
			vLepKingAGI
			vLep2AGI
			vLep3AGI
			vEgoLookAround
			vEgoShrink
		)
		(LoadMany PICTURE rLeprechaunThrone)
		(Load SOUND 48)
		(super init:)
		(ego
			view: vTinyEgo
			loop: loopW
			x: 214
			y: 51
			setCycle: Walk
			setStep: 2 1
			illegalBits: 0
			init:
		)
		(oldKing init: stopUpd:)
		(lep1 init: stopUpd:)
		(lep2 init:)
		(lep3 init:)
		(lep4 init: stopUpd:)
		(= oldSpeed speed)
		(= local0 0)
		(HandsOff)
		(self setScript: oldDance)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
	)
)

(instance oldDance of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lep3
					setCycle: Forward
					setMotion: Wander
					illegalBits: cYELLOW
				)
				(lep2
					setCycle: Forward
					setMotion: Wander
					illegalBits: cYELLOW
				)
				(DisplayOldGraphics)
				(ego
					setCycle: Walk
					setMotion: DPath 199 51 173 75
					self
				)
			)
			(1
				(ego
					view: vEgoShrink
					loop: 1
					cel: 10
					setCycle: BegLoop
					self
				)
			)
			(2
				(NormalEgo)
				(ego loop: loopS)
				(= cycles 5)
			)
			(3
				(ego setMotion: DPath 144 95 101 108 41 114 9 94 self)
			)
			(4
				((ScriptID 0 6) fade:)
				(= cycles 1)
			)
			(5
				(curRoom
					picture: rLeprechaunThrone
					style: HSHUTTER
					drawPic: rLeprechaunThrone
				)
				(lep1 dispose: delete:)
				(lep2 dispose: delete:)
				(lep3 dispose: delete:)
				(lep4 dispose: delete:)
				(oldKing dispose: delete:)
				(ego posn: 309 178 setMotion: MoveTo 249 178)
				(curRoom setScript: danceFever)
			)
		)
	)
)

(instance danceFever of Script

	(method (doit)
		(super doit:)
		(cond 
			((< local0 ((ScriptID 0 6) prevSignal?))
				(= local0 ((ScriptID 0 6) prevSignal?))
				((ScriptID 0 6) prevSignal: 0)
				(poofAway cue:)
			)
			((and (== (ego x?) 249) (not local2))
				(= local2 1)
				(DisplayNewGraphics 39 165)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= speed 1)
				(king init: stopUpd:)
				(girl1 init: stopUpd:)
				(girl2 init: stopUpd:)
				(man1 init: stopUpd:)
				(man2 init: stopUpd:)
				(man3 init: stopUpd:)
				(drummer init: stopUpd:)
				(harpist init: stopUpd:)
				(if (!= howFast slow)
					(girl1 setCycle: Forward)
					(girl2 setCycle: Forward)
					(man1 setCycle: Forward)
					(man2 setCycle: Forward)
					(man3 setCycle: Forward)
					(king setCycle: Forward)
					(drummer setCycle: Forward)
					(harpist setCycle: Forward)
				)
				((ScriptID 0 6) number: 48 loop: 1 play:)
				(man4 init: stopUpd:)
				(if (!= howFast slow)
					(man4 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(1
				(if (!= howFast slow)
					(man4
						loop: 0
						cel: 0
						setCycle: (if (!= howFast slow) Forward else 0)
					)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(2
				(self setScript: poofAway self)
			)
			(3
				(poof dispose:)
				(RedrawPic)
				(king
					setCycle: Forward
					setLoop: 2
					setMotion: MoveTo 178 120 self
				)
			)
			(4
				(king setMotion: MoveTo 152 160 self)
			)
			(5
				(king setMotion: MoveTo 174 176 self)
			)
			(6
				(king setMotion: MoveTo 104 186 self)
			)
			(7
				(king setMotion: MoveTo -10 173 self)
			)
			(8
				(theGame setSpeed: oldSpeed)
				(king dispose:)
				(curRoom setScript: dialog)
				(self dispose:)
			)
		)
	)
)

(instance poofAway of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(poof
					init:
					cel: 0
					posn: (man1 x?) (man1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(2
				(man1 dispose:)
				(poof setCycle: EndLoop self)
			)
			(3 0)
			(4
				(poof
					cel: 0
					posn: (man2 x?) (man2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(5
				(man2 dispose:)
				(poof setCycle: EndLoop self)
			)
			(6 0)
			(7
				(poof
					cel: 0
					posn: (man3 x?) (man3 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(8
				(man3 dispose:)
				(poof setCycle: EndLoop self)
			)
			(9 0)
			(10
				(poof
					cel: 0
					posn: (girl1 x?) (girl1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(11
				(girl1 dispose:)
				(poof setCycle: EndLoop self)
			)
			(12 0)
			(13
				(poof
					cel: 0
					posn: (girl2 x?) (girl2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(14
				(girl2 dispose:)
				(poof setCycle: EndLoop self)
			)
			(15 0)
			(16
				(poof
					cel: 0
					posn: (man4 x?) (man4 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(17
				(man4 dispose:)
				(poof setCycle: EndLoop self)
			)
			(18 0)
			(19
				(poof
					cel: 0
					posn: (drummer x?) (drummer y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(20
				(drummer dispose:)
				(poof setCycle: EndLoop self)
			)
			(21 0)
			(22
				(poof
					cel: 0
					posn: (harpist x?) (harpist y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(23
				(harpist dispose:)
				(poof setCycle: EndLoop self)
			)
			(24
				(self dispose:)
			)
		)
	)
)

(instance dialog of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 165 (ego y?) self)
			)
			(1
				(ego loop: loopS)
				(= cycles 2)
			)
			(2
				(ego
					view: vEgoLookAround
					loop: 1
					setCycle: Forward
				)
				(= cycles 7)
			)
			(3
				(Print 77 0
					#icon movingIcon
					#mode teJustCenter
					#at 45 30
					#width 170
					#time 5
				)
				(= cycles 7)
			)
			(4
				(Print 77 1
					#icon movingIcon
					#mode teJustCenter
					#at 45 30
					#width 170
					#time 5
				)
				(= cycles 7)
			)
			(5
				(NormalEgo)
				(ego loop: loopS)
				(Print 77 2
					#icon movingIcon
					#mode teJustCenter
					#at 45 30
					#width 170
					#time 7
				)
				(= cycles 1)
			)
			(6
				(ego
					illegalBits: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 70) self
				)
			)
			(7
				(curRoom
					picture: vSpeed
					style: (| IRISIN BLACKOUT)
					drawPic: vSpeed
				)
				(= cycles 3)
			)
			(8 (theGame restart:))
		)
	)
)

(instance movingIcon of DCIcon
	(properties
		view vEgoTalkIcon
		loop 2
	)
	
	(method (init)
		(super init:)
		(self cycleSpeed: (if (> howFast fast) 10 else 6))
	)
)

(instance king of Actor
	(properties
		x 168
		y 97
		view vLepKing
		illegalBits $0000
	)
)

(instance girl1 of Prop
	(properties
		x 41
		y 88
		view vLepGirls
	)
)

(instance girl2 of Prop
	(properties
		x 250
		y 131
		view vLepGirls
		loop 1
	)
)

(instance man1 of Prop
	(properties
		x 25
		y 86
		view vLepMan2
	)
)

(instance man2 of Prop
	(properties
		x 137
		y 114
		view vLepMan2
		loop 1
	)
)

(instance man3 of Prop
	(properties
		x 167
		y 134
		view vLepMan2
		loop 2
	)
)

(instance man4 of Prop
	(properties
		x 116
		y 138
		view vLepMan1
		loop 1
	)
)

(instance drummer of Prop
	(properties
		x 211
		y 126
		view vLepDrummer
	)
)

(instance harpist of Prop
	(properties
		x 231
		y 110
		view vLepHarpist
	)
)

(instance poof of Prop
	(properties
		view vLepKingSitting
		loop 2
	)
)

(instance oldKing of View
	(properties
		x 260
		y 110
		view vLepKingAGI
		priority 10
		signal fixPriOn
	)
)

(instance lep1 of View
	(properties
		x 105
		y 83
		view vLep1AGI
		cel 7
	)
)

(instance lep2 of Actor
	(properties
		x 225
		y 97
		view vLep2AGI
		cel 1
	)
)

(instance lep3 of Actor
	(properties
		x 61
		y 140
		view vLep2AGI
		cel 2
		moveSpeed 1
	)
)

(instance lep4 of View
	(properties
		x 124
		y 81
		view vLep3AGI
		cel 3
	)
)
