;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use LoadMany)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	battle 0
)

(local
	egoHealth =  10
	egoStamina =  10
	catHealth =  10
	local3 =  10
	local4 =  3
	local5 =  20
	local6
	local7
	local8
)
(instance battle of Room
	(properties
		picture 440
		style IRISOUT
	)
	
	(method (init)
		(LoadMany VIEW 442 540 541 803)
		(LoadMany SOUND 2 102 38 138)
		(super init:)
		(addToPics
			add: torso heroH heroHBox heroS heroSBox heroM heroMBox catH catHBox
			eachElementDo: #init
			doit:
		)
		(torso init:)
		(tail init:)
		(head init: setCycle: Forward)
		(LArm init:)
		(RArm init:)
		(egoBack init:)
		(shield init:)
		(sword init: setScript: SwordMotion)
		(heroHBar cel: egoHealth init:)
		(heroSBar init:)
		(heroMBar init:)
		(catHBar cel: catHealth init:)
		(music
			number: (if (== numVoices 1) 102 else 2)
			loop: -1
			play:
		)
		(Display 11 0
			p_width 300
			p_at -1 5
			p_dispose
			p_font 8
			p_color vLCYAN
			p_back vBLACK
			p_draw
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not (egoBack script?))
					(not (LArm script?))
					(!= (head script?) TakeABite)
					(not (Random 0 1))
				)
				(sword setScript: 0)
				(egoBack setScript: Thrust)
			)
			(
				(and
					(not (LArm script?))
					(not (RArm script?))
					(not (head script?))
					(not (tail cycler?))
				)
				(tail setCycle: Forward)
			)
			(
				(and
					(tail cycler?)
					(or (LArm script?) (RArm script?) (head script?))
				)
				(tail setCycle: 0)
			)
		)
		(if (and local3 (not (-- local3)))
			(= local3 (Random 5 25))
			(= local6 1)
		)
		(if (and local4 (not (-- local4)))
			(= local4 (Random 3 25))
			(= local7 1)
		)
		(if (and local5 (not (-- local5)))
			(= local5 (Random 10 30))
			(= local8 1)
		)
		(cond 
			(
			(and local6 (not (LArm script?)) (>= catHealth 2)) (LArm setScript: LeftSwipe) (= local6 0))
			(
			(and local7 (not (RArm script?)) (>= catHealth 2)) (RArm setScript: RightSwipe) (= local7 0))
			(
				(and
					local8
					(not (head script?))
					(!= (egoBack script?) Thrust)
				)
				(head setScript: TakeABite)
				(= local8 0)
			)
		)
		(super doit:)
	)
)

(instance heroH of PicView
	(properties
		y 30
		x 60
		view 803
	)
)

(instance heroHBox of PicView
	(properties
		y 29
		x 59
		view 803
		cel 3
	)
)

(instance heroS of PicView
	(properties
		y 49
		x 58
		view 803
		cel 1
	)
)

(instance heroSBox of PicView
	(properties
		y 48
		x 59
		view 803
		cel 3
	)
)

(instance heroM of PicView
	(properties
		y 69
		x 58
		view 803
		cel 2
	)
)

(instance heroMBox of PicView
	(properties
		y 68
		x 59
		view 803
		cel 3
	)
)

(instance catH of PicView
	(properties
		y 30
		x 227
		view 803
	)
)

(instance catHBox of PicView
	(properties
		y 29
		x 228
		view 803
		cel 3
	)
)

(instance heroHBar of View
	(properties
		y 28
		x 61
		view 803
		loop 1
		priority 10
	)
)

(instance heroSBar of View
	(properties
		y 47
		x 61
		view 803
		loop 1
		cel 10
		priority 10
	)
)

(instance heroMBar of View
	(properties
		y 67
		x 61
		view 803
		loop 1
		cel 9
		priority 10
	)
)

(instance catHBar of View
	(properties
		y 28
		x 230
		view 803
		loop 1
		priority 10
	)
)

(instance torso of PicView
	(properties
		y 112
		x 189
		view 442
		loop 1
		priority 0
		signal fixPriOn
	)
)

(instance head of Prop
	(properties
		y 44
		x 172
		view 442
		loop 5
		priority 8
	)
)

(instance tail of Prop
	(properties
		y 140
		x 235
		view 442
		loop 4
		priority 8
	)
)

(instance LArm of Prop
	(properties
		y 67
		x 202
		view 442
		loop 2
		priority 8
	)
)

(instance RArm of Prop
	(properties
		y 77
		x 139
		view 442
		loop 3
		priority 8
	)
)

(instance egoBack of Prop
	(properties
		y 194
		x 158
		view 541
		priority 15
		signal fixPriOn
	)
)

(instance shield of Prop
	(properties
		y 187
		x 137
		view 540
		loop 1
		priority 12
		signal fixPriOn
	)
)

(instance sword of Prop
	(properties
		y 194
		x 195
		view 540
		loop 3
		priority 12
		signal fixPriOn
	)
)

(instance RightSwipe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (RArm setCycle: CycleTo 2 1 self))
			(1
				(RArm setCycle: CycleTo 0 1 self)
				(if (not (egoBack script?))
					(if (Random 0 1)
						(egoBack setScript: ShieldBlock)
					else
						(egoBack setScript: TakeAHit)
						(if (> egoHealth 2) (-- egoHealth))
						(heroHBar cel: egoHealth forceUpd:)
					)
				)
			)
			(2 (client setScript: 0))
		)
	)
)

(instance LeftSwipe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (LArm setCycle: CycleTo 2 1 self))
			(1
				(LArm setCycle: CycleTo 0 1 self)
				(if (and (not (egoBack script?)) (Random 0 1))
					(egoBack setScript: TakeAHit)
					(if (> egoHealth 2) (-- egoHealth))
					(heroHBar cel: egoHealth forceUpd:)
				)
			)
			(2 (client setScript: 0))
		)
	)
)

(instance TakeABite of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(head loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				(if (and (not (egoBack script?)) (Random 0 1))
					(egoBack setScript: TakeAHit)
					(if (> egoHealth 2) (-- egoHealth))
					(heroHBar cel: egoHealth forceUpd:)
				)
				(head loop: 5 cel: 0 setCycle: Forward)
				(client setScript: 0)
			)
		)
	)
)

(instance Thrust of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (Random 0 1) (>= egoStamina 2))
					(-- egoStamina)
					(heroSBar cel: egoStamina forceUpd:)
				)
				(if (<= egoHealth 7)
					(sword loop: 3 cel: 1)
				else
					(sword loop: (Random 2 3) cel: 1)
				)
				(= cycles 2)
			)
			(1
				(if (== (sword loop?) 3)
					(head setScript: CatHit)
					(if (> catHealth 1) (= catHealth (- catHealth 2)))
					(catHBar cel: catHealth forceUpd:)
				else
					(sword loop: 2 cel: 0)
				)
				(= cycles 6)
			)
			(2
				(if (< catHealth 2)
					(music
						number: (if (== numVoices 1) 138 else 38)
						loop: 1
						play:
					)
					(= seconds 4)
				else
					(sword loop: 2 cel: 0 setScript: SwordMotion)
					(client setScript: 0)
				)
			)
			(3 (curRoom newRoom: 12))
		)
	)
)

(instance CatHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(head loop: 6 cel: 0 setCycle: Forward)
				(if (< catHealth 2)
					(client setScript: 0)
				else
					(= cycles (Random 5 10))
				)
			)
			(1
				(head loop: 5 cel: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance TakeAHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoBack posn: (- (egoBack x?) 3) (+ (egoBack y?) 5))
				(shield posn: (- (shield x?) 3) (+ (shield y?) 5))
				(sword posn: (- (sword x?) 5) (+ (sword y?) 10))
				(= cycles 8)
			)
			(1
				(egoBack posn: (+ (egoBack x?) 3) (- (egoBack y?) 5))
				(shield posn: (+ (shield x?) 3) (- (shield y?) 5))
				(sword posn: (+ (sword x?) 5) (- (sword y?) 10))
				(client setScript: 0)
			)
		)
	)
)

(instance ShieldBlock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shield setCel: 1)
				(= cycles 4)
			)
			(1
				(shield setCel: 2)
				(= cycles 6)
			)
			(2
				(shield setCel: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance SwordMotion of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sword cel: 0)
				(= cycles (Random 5 10))
			)
			(1
				(sword cel: 2)
				(= cycles (Random 3 6))
				(= state -1)
			)
		)
	)
)

(instance blackOut of SysWindow
	(properties
		color vLCYAN
		back vBLACK
	)
)
