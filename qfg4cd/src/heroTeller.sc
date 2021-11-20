;;; Sierra Script 1.0 - (do not remove this comment)
(script# 324)
(include sci.sh)
(use Main)
(use TellerIcon)
(use System)

(public
	heroTeller 0
	domovoiTeller 1
)

(instance heroTeller of Teller
	(properties)
	
	(method (init param1 param2 param3)
		(super
			init:
				param2
				param3
				17
				128
				(switch param1
					(1 6)
					(2 7)
					(3 4)
					(4 5)
					(5 24)
					(6 11)
					(7 12)
					(8 15)
					(9 8)
					(10
						(if (OneOf timeODay 4 5) 10 else 9)
					)
					(11
						(if (OneOf timeODay 4 5) 16 else 15)
					)
					(12
						(if (OneOf timeODay 4 5) 14 else 13)
					)
					(13 2)
					(15 3)
					(16 1)
				)
		)
	)
)

(instance domovoiTeller of Teller
	(properties
		title 1
	)
	
	(method (init param1 param2 param3)
		(super
			init: param2 param3 17 148
			(switch param1
				(3 4)
				(13 2)
				(15 3)
			)
		)
		(= talker (ScriptID 72 0))
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(cond 
				(
					(and
						(>= timeODay 6)
						(not (Btst 128))
						(not (Btst 136))
					)
					(Bset 128)
				)
				(
					(and
						(>= timeODay 6)
						(Btst 128)
						(not (Btst 135))
						(>= Day (+ gCurrentDay_7 1))
						(not (Btst 138))
					)
					(Bset 135)
				)
				(
					(and
						(>= timeODay 6)
						(Btst 128)
						(Btst 138)
						(not (Btst 163))
					)
					(Bset 139)
				)
			)
			(= gCurrentDay_7 Day)
		)
		(return 1)
	)
)
