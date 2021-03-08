;;; Sierra Script 1.0 - (do not remove this comment)
(script# 660)
(include sci.sh)
(use Main)
(use Talker)
(use Scaler)
(use Osc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm660 0
	beaTalker 10
	floTalker 13
	rogTalker 15
)

(local
	local0
	local1
	local2
)
(instance theMusic3 of Sound
	(properties)
)

(instance rm660 of Rm
	(properties
		picture 102
	)
	
	(method (init)
		(= style -32758)
		(LoadMany 143 number)
		(LoadMany 128 560 562 563 564 567)
		(super init:)
		(self setScript: sFallOff)
	)
)

(instance sDoRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 15))
			(1 (rope init:) (= ticks 5))
			(2 (rope setCycle: End self))
			(3 (self dispose:))
		)
	)
)

(instance sFallOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(RogersBody init: stopUpd:)
				(RogersArm init: stopUpd:)
				(RogersHead init: stopUpd:)
				(RogersLegs init: stopUpd:)
				(BeasHead init: setCycle: Fwd)
				(BeasArm init: stopUpd:)
				(BeasEyes init: setCycle: Fwd)
				(BeaLegs init: setCycle: Fwd)
				(= seconds 3)
			)
			(1 (messager say: 3 0 0 1 self))
			(2 (= seconds 5))
			(3
				(oid1 init:)
				(ego get: 19)
				(ego get: 10)
				(= seconds 3)
			)
			(4
				(messager say: 14 0 0 1 self)
			)
			(5
				(RogersLegs loop: 3 setCel: 1 x: 98 y: 74)
				(BeasHead loop: 0 setCel: 1 x: 110 y: 57)
				(BeasArm loop: 2 setCel: 0 x: 112 y: 89)
				(BeasEyes loop: 1 setCel: 0 x: 132 y: 69)
				(RogersEyes init: setCycle: Fwd)
				(RogersPants init: stopUpd:)
				(BeaLegs loop: 6 setCel: 0 x: 120 y: 85)
				(BeasOtherArm init: stopUpd:)
				(= seconds 2)
			)
			(6
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeasArm loop: 2 setCel: 0 x: 133 y: 98)
				(BeasEyes loop: 1 setCel: 0 x: 150 y: 78)
				(RogersEyes loop: 4 setCel: 1 x: 86 y: 34)
				(BeasOtherArm loop: 5 setCel: 0 x: 126 y: 84)
				(BeaLegs loop: 6 setCel: 0 x: 136 y: 92)
				(BeasHead loop: 0 setCel: 1 x: 128 y: 66)
				(= seconds 2)
			)
			(7 (messager say: 3 0 0 2 self))
			(8 (self dispose:))
		)
	)
)

(instance sByeBea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 0 0 3 self)
			)
			(1
				(BeasHead dispose:)
				(BeasArm dispose:)
				(BeasEyes dispose:)
				(BeasOtherArm dispose:)
				(RogersBody stopUpd:)
				(RogersArm stopUpd:)
				(RogersLegs stopUpd:)
				(RogersEyes stopUpd:)
				(RogersPants stopUpd:)
				(BeaLegs
					setLoop: 8
					moveSpeed: 0
					setStep: 8 8
					setCycle: 0
					setPri: 15
					setCel: 0
					posn: 143 60
					setScale: Scaler 7 100 131 60
				)
				(puke4HitBea dispose:)
				(= ticks 2)
			)
			(2
				(BeaLegs setMotion: MoveTo 157 81 self)
			)
			(3
				(BeaLegs
					setStep: 5 5
					moveSpeed: 2
					setMotion: MoveTo 176 110 self
				)
			)
			(4
				(BeaLegs
					moveSpeed: 4
					setStep: 2 2
					setMotion: MoveTo 191 131 self
				)
			)
			(5
				(BeaLegs dispose:)
				(puke1HitRog init:)
				(RogersHead startUpd:)
			)
			(6 (= cycles 4))
			(7
				(RogersEyes dispose:)
				(RogersHead cel: 1)
				(= seconds 4)
			)
			(8 (EgoDead 31))
		)
	)
)

(instance sDoComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(RogersHead startUpd:)
				(RogersEyes hide:)
				(= cycles 4)
			)
			(1
				(Bset 99)
				(RogersArm posn: 44 76 setCel: 2)
				(RogersHead setCel: 2)
				(theMusic2 number: 603 setLoop: 1 play:)
				(= seconds 3)
			)
			(2
				(SolvePuzzle 226 25)
				(messager say: 9 32 0 0 self)
			)
			(3
				(RogersHead setCel: 0)
				(RogersArm posn: 0 56 setCel: 0)
				(RogersEyes show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGiveFrock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(RogersEyes dispose:)
				(RogersArm loop: 2 setCel: 4 x: 53 y: 75)
				(RogersHead loop: 1 setCel: 1 x: 84 y: 37)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeasArm loop: 2 setCel: 0 x: 133 y: 98)
				(BeasEyes loop: 1 setCel: 0 x: 148 y: 78)
				(BeasOtherArm loop: 5 setCel: 0 x: 126 y: 84)
				(frock init:)
				(BeaLegs loop: 6 setCel: 0 x: 136 y: 92)
				(BeasHead loop: 0 setCel: 1 x: 128 y: 66)
				(SolvePuzzle 225 35)
				(ego put: 19)
				(= seconds 1)
			)
			(1
				(RogersArm loop: 2 setCel: 4 x: 53 y: 75)
				(RogersHead loop: 1 setCel: 1 x: 84 y: 37)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeasArm loop: 3 setCel: 0 x: 97 y: 100)
				(BeasEyes loop: 1 setCel: 0 x: 148 y: 78)
				(BeasOtherArm loop: 5 setCel: 0 x: 126 y: 84)
				(BeaLegs loop: 6 setCel: 0 x: 136 y: 92)
				(BeasHead loop: 0 setCel: 1 x: 128 y: 66)
				(= local2 (= seconds 1))
			)
			(2
				(BeasArm dispose:)
				(BeasOtherArm dispose:)
				(BeasEyes dispose:)
				(BeaLegs setCycle: 0 setPri: 15)
				(BeasHead dispose:)
				(frock dispose:)
				(puke4HitBea dispose:)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 103 (curRoom style?))
				(both init:)
				(= seconds 1)
			)
			(3 (= seconds 2))
			(4 (both setCycle: End self))
			(5 (= seconds 1))
			(6
				(both addToPic: dispose:)
				(= cycles 2)
			)
			(7
				(curRoom drawPic: 102 (curRoom style?))
				(cast eachElementDo: #show)
				(= local2 0)
				(RogersArm loop: 2 setCel: 0 x: 1 y: 58)
				(RogersHead loop: 1 setCel: 0 x: 75 y: 39)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeanButt init: loop: 4 setCel: 0 x: 3 y: -6)
				(BeaLegs view: 562 loop: 4 setCel: 1 x: 0 y: 35)
				(= seconds 1)
			)
			(8
				(RogersArm loop: 2 setCel: 0 x: 1 y: 58)
				(RogersHead loop: 1 setCel: 0 x: 75 y: 39)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeanButt loop: 4 setCel: 3 x: 3 y: -5)
				(BeaLegs loop: 4 setCel: 1 x: 0 y: 35)
				(= seconds 1)
			)
			(9
				(RogersArm loop: 2 setCel: 0 x: 1 y: 58)
				(RogersHead loop: 1 setCel: 0 x: 75 y: 39)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeanButt loop: 4 setCel: 4 x: 3 y: -5)
				(BeaLegs loop: 4 setCel: 1 x: 0 y: 35)
				(= seconds 1)
			)
			(10
				(RogersArm loop: 2 setCel: 0 x: 1 y: 58)
				(RogersHead loop: 1 setCel: 0 x: 75 y: 39)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeaLegs loop: 4 setCel: 1 x: -22 y: 35)
				(BeanButt loop: 4 setCel: 2 x: 5 y: 7)
				(= seconds 1)
			)
			(11
				(RogersArm loop: 2 setCel: 0 x: 1 y: 58)
				(RogersHead loop: 1 setCel: 0 x: 75 y: 39)
				(RogersLegs loop: 3 setCel: 0 x: 97 y: 79)
				(BeanButt loop: 4 setCel: 2 x: 5 y: 7)
				(= seconds 1)
			)
			(12
				(BeaLegs dispose:)
				(= seconds 1)
			)
			(13
				(RogersArm setScript: sDoRope)
				(= local0 1)
				(BeanButt dispose:)
				(theGame handsOn:)
				(LoadMany 128 562 0)
				(self dispose:)
			)
		)
	)
)

(instance sDoOids of Script
	(properties)
	
	(method (doit)
		(if (not local2) (super doit:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oid2 init: setLoop: (+ (oid2 loop?) 7) setCycle: End)
				(oid3 init: setLoop: (+ (oid3 loop?) 7) setCycle: End)
				(theMusic2 number: 260 setLoop: 1 play:)
				(= seconds 2)
			)
			(1
				(oid1
					show:
					setLoop: (+ (oid1 loop?) 7)
					setCycle: End self
				)
				(oid4
					init:
					setLoop: (+ (oid4 loop?) 7)
					setCycle: End self
				)
				(theMusic2 number: 260 setLoop: 1 play:)
			)
			(2 0)
			(3
				(oid1 setLoop: (- (oid1 loop?) 7) stopUpd:)
				(oid2 setLoop: (- (oid2 loop?) 7) stopUpd:)
				(oid3 setLoop: (- (oid3 loop?) 7) stopUpd:)
				(oid4 setLoop: (- (oid4 loop?) 7) stopUpd:)
				(= seconds 1)
			)
			(4
				(oid1 setCycle: Osc 1)
				(puke1Miss init:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(= seconds 1)
			)
			(5
				(oid2 setCycle: Osc 1)
				(puke2Miss init:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(= seconds 1)
			)
			(6
				(oid4 setCycle: Osc 1)
				(puke4HitBea init:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(= seconds 3)
			)
			(7
				(oid3 setCycle: Osc 1)
				(puke3Miss init:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(= seconds 2)
			)
			(8
				(oid4 setCycle: Osc 1)
				(puke4Miss init:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(= seconds 4)
			)
			(9
				(BeasEyes setLoop: 1)
				(oid4 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke4HitWall init:)
				(= seconds 2)
			)
			(10
				(oid1 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke1Miss init:)
				(= seconds 3)
			)
			(11
				(oid2 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke2Miss init:)
				(= seconds 2)
			)
			(12
				(oid4 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke4HitWall init:)
				(= seconds 1)
			)
			(13
				(cond 
					(local0 (= cycles 1))
					((== sGiveFrock (curRoom script?)) (= seconds 3))
					(else (curRoom setScript: sByeBea))
				)
			)
			(14
				(puke1Miss moveSpeed: 3)
				(puke2Miss moveSpeed: 3)
				(puke3Miss moveSpeed: 3)
				(puke4Miss moveSpeed: 3)
				(puke4HitWall moveSpeed: 3)
				(puke4HitBea moveSpeed: 3)
				(puke1HitRog moveSpeed: 3)
				(oid3 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke3Miss init:)
				(= seconds 2)
			)
			(15
				(oid4 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke4Miss init:)
				(= seconds 4)
			)
			(16
				(oid4 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke4HitWall init:)
				(= seconds 4)
			)
			(17
				(oid1 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke1Miss init:)
				(= seconds 3)
			)
			(18
				(oid2 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke2Miss init:)
				(= seconds 3)
			)
			(19
				(oid4 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke4HitWall init:)
				(= seconds 4)
			)
			(20
				(oid1 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke1Miss init:)
				(= seconds 3)
			)
			(21
				(oid2 setCycle: Osc 1)
				(theMusic2 number: 519 setLoop: 1 play:)
				(puke2Miss init:)
				(= seconds 3)
			)
			(22
				(theGame handsOff:)
				(theMusic2 number: 519 setLoop: 1 play:)
				(oid1 setCycle: Osc 1)
				(puke1HitRog init:)
			)
			(23
				(RogersHead setCel: 1)
				(= seconds 4)
			)
			(24 (EgoDead 40))
		)
	)
)

(instance both of Prop
	(properties
		x 64
		y 141
		view 560
		loop 7
		priority 2
		signal $4010
		cycleSpeed 10
	)
)

(instance RogersBody of Prop
	(properties
		y 23
		noun 9
		view 563
		priority 2
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(if (not local1) (curRoom setScript: sDoComm))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance RogersArm of Prop
	(properties
		y 56
		noun 9
		view 563
		loop 2
		priority 3
		signal $4010
	)
	
	(method (doVerb theVerb)
		(RogersBody doVerb: theVerb &rest)
	)
)

(instance RogersHead of Prop
	(properties
		x 74
		y 37
		noun 9
		view 563
		loop 1
		priority 3
		signal $4010
	)
	
	(method (doVerb theVerb)
		(RogersBody doVerb: theVerb &rest)
	)
)

(instance RogersLegs of Prop
	(properties
		x 98
		y 74
		noun 9
		view 563
		loop 3
		cel 1
		priority 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(RogersBody doVerb: theVerb &rest)
	)
)

(instance BeasHead of Prop
	(properties
		x 107
		y 56
		noun 3
		view 562
		cel 1
		priority 10
		signal $4010
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(BeaLegs doVerb: theVerb &rest)
	)
)

(instance BeasArm of Prop
	(properties
		x 112
		y 89
		noun 3
		view 562
		loop 2
		priority 10
		signal $4010
	)
	
	(method (doVerb theVerb)
		(BeaLegs doVerb: theVerb &rest)
	)
)

(instance BeasEyes of Prop
	(properties
		x 129
		y 68
		noun 3
		view 562
		loop 1
		priority 11
		signal $4010
		cycleSpeed 62
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(BeaLegs doVerb: theVerb &rest)
	)
)

(instance BeaLegs of Actor
	(properties
		x 116
		y 84
		noun 3
		view 562
		loop 6
		priority 1
		signal $4010
		cycleSpeed 20
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(23
				(if (not (curRoom script?))
					(curRoom setScript: sGiveFrock)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance RogersPants of Prop
	(properties
		x 99
		y 78
		noun 9
		view 563
		loop 3
		cel 2
		priority 10
		signal $4010
	)
	
	(method (doVerb theVerb)
		(RogersBody doVerb: theVerb &rest)
	)
)

(instance RogersEyes of Prop
	(properties
		x 86
		y 34
		noun 9
		view 563
		loop 4
		cel 1
		priority 7
		signal $4010
		cycleSpeed 80
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(RogersBody doVerb: theVerb &rest)
	)
)

(instance BeasOtherArm of Prop
	(properties
		x 112
		y 74
		noun 3
		view 562
		loop 5
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(BeaLegs doVerb: theVerb &rest)
	)
)

(instance BeanButt of Prop
	(properties
		x 3
		y -6
		noun 3
		view 562
		loop 4
		priority 15
		signal $4010
	)
)

(instance oid1 of Prop
	(properties
		x 165
		y 15
		noun 8
		view 564
		loop 2
		cel 3
	)
	
	(method (init)
		(super init:)
		(self hide: setScript: sDoOids)
	)
)

(instance oid2 of Prop
	(properties
		x 214
		y 57
		noun 8
		view 564
		cel 2
	)
)

(instance oid3 of Prop
	(properties
		x 234
		y 67
		noun 8
		view 564
		loop 1
		cel 1
	)
)

(instance oid4 of Prop
	(properties
		x 292
		y 136
		noun 8
		view 564
		loop 3
	)
)

(instance frock of Prop
	(properties
		x 53
		y 74
		view 563
		loop 2
		cel 3
		signal $4010
	)
)

(class MyPuke of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (dispose)
		(theMusic3 number: 213 setLoop: 1 play:)
		(super dispose:)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance puke1Miss of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			posn: 162 1
			setLoop: 11
			setStep: 8 8
			setPri: 1
			setMotion: MoveTo 21 21 self
			setCycle: End
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance puke2Miss of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			posn: 212 38
			setPri: 1
			setStep: 8 8
			setLoop: 13
			setMotion: MoveTo 29 38 self
			setCycle: End
		)
	)
)

(instance puke3Miss of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			posn: 236 51
			setPri: 1
			setStep: 8 8
			setLoop: 13
			setMotion: MoveTo 19 15 self
			setCycle: End
		)
	)
)

(instance puke4Miss of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 0
			setStep: 8 8
			posn: 290 125
			setLoop: 6
			setMotion: MoveTo 45 87 self
			setCycle: End
		)
	)
)

(instance puke4HitWall of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 0
			posn: 290 125
			setStep: 8 8
			setLoop: 6
			setMotion: MoveTo 75 39 self
			setCycle: End
		)
	)
	
	(method (cue)
		(if (== loop 6)
			(self setLoop: 5 setCel: 0 posn: 28 41 setCycle: End self)
		else
			(self dispose:)
		)
	)
)

(instance puke4HitBea of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			view: 564
			posn: 290 125
			setStep: 8 8
			setPri: 15
			setLoop: 6
			setMotion: MoveTo 190 104 self
			setCycle: End
		)
	)
	
	(method (cue)
		(if (== loop 6)
			(self
				view: 562
				setLoop: 7
				setCel: 0
				posn: 160 92
				setCycle: End self
			)
		else
			(theGame handsOn:)
			(BeasEyes setLoop: 9)
			(messager say: 2 0 0 0)
		)
	)
)

(instance puke1HitRog of MyPuke
	(properties
		view 564
		signal $6000
		moveSpeed 0
	)
	
	(method (init)
		(super init:)
		(self
			posn: 162 1
			setPri: 14
			setStep: 8 8
			setLoop: 11
			setMotion: MoveTo 73 16 self
			setCycle: End
		)
	)
	
	(method (cue)
		(cond 
			((== loop 11)
				(self
					setLoop: 12
					setCel: 0
					posn: 31 18
					setCycle: End self
				)
			)
			((curRoom script?) (sByeBea cue:))
			(else (sDoOids cue:))
		)
	)
)

(instance rogTalker of Narrator
	(properties
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 110
			tailY: 67
			xOffset: 25
			isBottom: 0
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance floTalker of Narrator
	(properties
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 70
			tailY: 80
			xOffset: 25
			isBottom: 0
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance beaTalker of Narrator
	(properties
		talkWidth 150
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 172
			tailY: 59
			xOffset: 25
			isBottom: 1
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance rope of Prop
	(properties
		x 62
		y 64
		noun 10
		view 567
		priority 12
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (curRoom script?))
					(curRoom setScript: sGrabRope)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sGrabRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(RogersArm startUpd:)
				(= cycles 6)
			)
			(1
				(RogersArm loop: 2 cel: 1 x: 38 y: 59 setPri: 15)
				(SolvePuzzle 227 5)
				(= seconds 3)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 640)
			)
		)
	)
)
