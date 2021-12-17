;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Main)
(use DialogPlane)
(use L7Room)
(use GenDialog)

(public
	ro50 0
)

(procedure (localproc_00ae &tmp temp0)
	(return
		(switch
			(= temp0
				(proc64033_7
					4
					375
					215
					(MakeMessageText 0 0 0 1 300)
					(MakeMessageText 0 0 0 2 300)
					(MakeMessageText 0 0 0 3 300)
					(MakeMessageText 0 0 0 4 300)
				)
			)
			(0
				((ScriptID 64017 0) set: 5)
				((ScriptID 64017 0) set: 6)
				((ScriptID 64017 0) set: 7)
				((ScriptID 64017 0) clear: 8)
				(curRoom newRoom: 100)
				(return 1)
			)
			(1
				((ScriptID 64017 0) set: 5)
				((ScriptID 64017 0) set: 6)
				((ScriptID 64017 0) set: 7)
				((ScriptID 64017 0) clear: 8)
				(curRoom newRoom: 9000)
				(return 1)
			)
			(2
				((ScriptID 64017 0) set: 5)
				((ScriptID 64017 0) clear: 6)
				((ScriptID 64017 0) set: 7)
				((ScriptID 64017 0) set: 8)
				(curRoom newRoom: 100)
				(return 1)
			)
			(3
				((ScriptID 64017 0) set: 5)
				((ScriptID 64017 0) clear: 6)
				((ScriptID 64017 0) clear: 7)
				((ScriptID 64017 0) clear: 8)
				(= quit 1)
				(return 1)
			)
		)
	)
)

(instance ro50 of L7Room
	(properties
		picture 12200
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 64017 0) set: 5)
		((ScriptID 64017 0) set: 6)
		((ScriptID 64017 0) set: 7)
		((ScriptID 64017 0) clear: 8)
		((ScriptID 64017 0) clear: 15)
		(theGame handsOn:)
		(localproc_00ae)
	)
)
