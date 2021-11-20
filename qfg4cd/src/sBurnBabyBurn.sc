;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include sci.sh)
(use Main)
(use EgoDead)
(use Array)
(use Motion)
(use Actor)
(use System)

(public
	proc87_0 0
	proc87_1 1
	sBurnBabyBurn 2
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
)
(procedure (proc87_0)
	(= local1 (IDArray new: 14))
	(= local2 (IntArray with: 1 1 1 1 1 1 1 2 2 2 2 3 2 3))
	(= local3 (IntArray with: 1 2 4 3 7 5 6 2 0 4 3 6 5 1))
	(= local4
		(IntArray
			with: 13 19 204 168 33 161 152 124 141 144 33 3 58 114
		)
	)
	(= local5
		(IntArray
			with: 162 154 160 155 173 182 149 141 147 189 151 172 185 139
		)
	)
	(davey init:)
	(crowdGuy init:)
	(= local0 0)
	(while (<= local0 13)
		(local1
			at:
				local0
				((crowdGuy new:)
					loop: (local2 at: local0)
					cel: (local3 at: local0)
					x: (local4 at: local0)
					y: (local5 at: local0)
					init:
				)
		)
		(++ local0)
	)
)

(procedure (proc87_1)
	(local2 dispose:)
	(local3 dispose:)
	(local4 dispose:)
	(local5 dispose:)
	(local1 dispose:)
)

(instance sBurnBabyBurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 50)
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 20 6 26 0 self)
			)
			(2
				(fire init: setCycle: End self)
			)
			(3 (proc87_1) (= seconds 1))
			(4 (EgoDead 127 260 262 0 912))
		)
	)
)

(instance crowdGuy of View
	(properties
		x 178
		y 171
		view 263
	)
)

(instance davey of Prop
	(properties
		x 79
		y 159
		view 262
		signal $4001
	)
)

(instance fire of Prop
	(properties
		x 79
		y 159
		view 262
		loop 1
		signal $4001
	)
)
