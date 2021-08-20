;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA) ;11
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
	leftArmTimer =  10
	rightArmTimer =  3
	headTimer =  20
	leftArmCued
	rightArmCued
	headCued
)
(instance battle of Room
	(properties
		picture vCheetaur
		style IRISOUT
	)
	
	(method (init)
		(LoadMany VIEW vMoreCheetaur vEgoBattleArms vEgoBattleBody vStatusBar)
		(LoadMany SOUND sHardBattle sHardBattleIBM sHardBattleEnd sHardBattleEndIBM)
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
		(globalMusic
			number: (if (== numVoices 1) sHardBattleIBM else sHardBattle)
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
		(if (and leftArmTimer (not (-- leftArmTimer)))
			(= leftArmTimer (Random 5 25))
			(= leftArmCued TRUE)
		)
		(if (and rightArmTimer (not (-- rightArmTimer)))
			(= rightArmTimer (Random 3 25))
			(= rightArmCued TRUE)
		)
		(if (and headTimer (not (-- headTimer)))
			(= headTimer (Random 10 30))
			(= headCued TRUE)
		)
		(cond 
			((and leftArmCued (not (LArm script?)) (>= catHealth 2))
				(LArm setScript: LeftSwipe)
				(= leftArmCued FALSE)
			)
			((and rightArmCued (not (RArm script?)) (>= catHealth 2))
				(RArm setScript: RightSwipe)
				(= rightArmCued FALSE)
			)
			(
				(and
					headCued
					(not (head script?))
					(!= (egoBack script?) Thrust)
				)
				(head setScript: TakeABite)
				(= headCued FALSE)
			)
		)
		(super doit:)
	)
)

(instance heroH of PicView
	(properties
		y 30
		x 60
		view vStatusBar
	)
)

(instance heroHBox of PicView
	(properties
		y 29
		x 59
		view vStatusBar
		cel 3
	)
)

(instance heroS of PicView
	(properties
		y 49
		x 58
		view vStatusBar
		cel 1
	)
)

(instance heroSBox of PicView
	(properties
		y 48
		x 59
		view vStatusBar
		cel 3
	)
)

(instance heroM of PicView
	(properties
		y 69
		x 58
		view vStatusBar
		cel 2
	)
)

(instance heroMBox of PicView
	(properties
		y 68
		x 59
		view vStatusBar
		cel 3
	)
)

(instance catH of PicView
	(properties
		y 30
		x 227
		view vStatusBar
	)
)

(instance catHBox of PicView
	(properties
		y 29
		x 228
		view vStatusBar
		cel 3
	)
)

(instance heroHBar of View
	(properties
		y 28
		x 61
		view vStatusBar
		loop 1
		priority 10
	)
)

(instance heroSBar of View
	(properties
		y 47
		x 61
		view vStatusBar
		loop 1
		cel 10
		priority 10
	)
)

(instance heroMBar of View
	(properties
		y 67
		x 61
		view vStatusBar
		loop 1
		cel 9
		priority 10
	)
)

(instance catHBar of View
	(properties
		y 28
		x 230
		view vStatusBar
		loop 1
		priority 10
	)
)

(instance torso of PicView
	(properties
		y 112
		x 189
		view vMoreCheetaur
		loop 1
		priority 0
		signal fixPriOn
	)
)

(instance head of Prop
	(properties
		y 44
		x 172
		view vMoreCheetaur
		loop 5
		priority 8
	)
)

(instance tail of Prop
	(properties
		y 140
		x 235
		view vMoreCheetaur
		loop 4
		priority 8
	)
)

(instance LArm of Prop
	(properties
		y 67
		x 202
		view vMoreCheetaur
		loop 2
		priority 8
	)
)

(instance RArm of Prop
	(properties
		y 77
		x 139
		view vMoreCheetaur
		loop 3
		priority 8
	)
)

(instance egoBack of Prop
	(properties
		y 194
		x 158
		view vEgoBattleBody
		priority 15
		signal fixPriOn
	)
)

(instance shield of Prop
	(properties
		y 187
		x 137
		view vEgoBattleArms
		loop 1
		priority 12
		signal fixPriOn
	)
)

(instance sword of Prop
	(properties
		y 194
		x 195
		view vEgoBattleArms
		loop 3
		priority 12
		signal fixPriOn
	)
)

(instance RightSwipe of Script
	
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
						(if (> egoHealth 2)
							(-- egoHealth)
						)
						(heroHBar cel: egoHealth forceUpd:)
					)
				)
			)
			(2 (client setScript: 0))
		)
	)
)

(instance LeftSwipe of Script
	
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(head loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				(if (and (not (egoBack script?)) (Random 0 1))
					(egoBack setScript: TakeAHit)
					(if (> egoHealth 2)
						(-- egoHealth)
					)
					(heroHBar cel: egoHealth forceUpd:)
				)
				(head loop: 5 cel: 0 setCycle: Forward)
				(client setScript: 0)
			)
		)
	)
)

(instance Thrust of Script

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
					(globalMusic
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
