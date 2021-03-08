;;; Sierra Script 1.0 - (do not remove this comment)
(script# 232)
(include sci.sh)
(use Main)
(use rm201)
(use eureka)
(use Motion)
(use System)

(public
	sTrashPickUp 3
	sAfterAlien 4
	sAlienHere 5
	sSpikeHere 7
	sDidntGetSpike 9
	sGenetixAlien 11
	sSpaceBarQuirk 12
	sSpikeComplaint 24
	sGarbageComplaint 25
)

(instance sTrashPickUp of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 211 setLoop: 1 play:)
				(= seconds 3)
			)
			(1
				(theMusic2 number: 225 loop: -1 play:)
				(= seconds 3)
			)
			(2
				(if
				(or (== global127 0) (and (== global127 1) (Btst 9)))
					(SolvePuzzle 166 100)
					(curRoom newRoom: 280)
				else
					(SolvePuzzle 167 100)
					(curRoom newRoom: 290)
				)
				(theMusic2 fade:)
				(self dispose:)
			)
		)
	)
)

(instance sSpikeHere of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
				(= global126 1)
			)
			(1 (proc201_6 self))
			(2
				(messager say: 16 0 26 1 self)
			)
			(3
				(self setScript: (ScriptID 201 4) self)
			)
			(4 (= seconds 2))
			(5
				((ScriptID 202 13) init:)
				(= seconds 1)
			)
			(6
				(messager say: 16 0 26 2 self)
			)
			(7
				((ScriptID 202 13) dispose:)
				(= seconds 1)
			)
			(8
				(theGame handsOn:)
				(eureka state: 1)
				(self dispose:)
			)
		)
	)
)

(instance sGarbageComplaint of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 202 13) init:)
				(= seconds 2)
				(eureka garbage: 0 gdoor: 0)
			)
			(1
				(messager say: 28 0 0 1 self 202)
			)
			(2
				(if
					(and
						(or (not (Btst 56)) (Btst 113) (Btst 114))
						(> global126 1)
					)
					(if (or (Btst 113) (Btst 114))
						(Bclr 113)
						(Bclr 114)
						(if (Btst 56)
							(messager say: 14 0 90 1 self 202)
						else
							(messager say: 14 0 39 2 self 202)
						)
					else
						(messager say: 14 0 39 1 self 202)
					)
				else
					(= cycles 1)
				)
			)
			(3
				((ScriptID 202 13) dispose:)
				(= seconds 1)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSpikeComplaint of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 202 13) init:)
				(= seconds 2)
			)
			(1
				(if (or (Btst 113) (Btst 114))
					(Bclr 113)
					(Bclr 114)
					(if (Btst 56)
						(messager say: 14 0 90 1 self 202)
					else
						(messager say: 14 0 39 2 self 202)
					)
				else
					(messager say: 14 0 39 1 self 202)
				)
			)
			(2
				((ScriptID 202 13) dispose:)
				(= seconds 1)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDidntGetSpike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				((ScriptID 202 13) init:)
				(= ticks 35)
			)
			(2
				(messager say: 14 0 0 1 self 202)
			)
			(3
				(client setScript: (ScriptID 210 3) 0 8)
				(self dispose:)
			)
		)
	)
)

(instance sGenetixAlien of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
				(theGame handsOff:)
			)
			(1 (proc201_6 self))
			(2 (= cycles 2))
			(3
				(messager say: 2 0 1 1 self 202)
			)
			(4
				(self setScript: (ScriptID 201 4) self)
			)
			(5
				(self setScript: (ScriptID 209 0) self 208)
			)
			(6
				(messager say: 2 0 0 0 self 202)
			)
			(7
				(self setScript: (ScriptID 209 1) self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSpaceBarQuirk of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
				(theGame handsOff:)
			)
			(1 (proc201_6 self))
			(2 (= cycles 2))
			(3
				(messager say: 19 0 1 1 self 202)
			)
			(4
				(self setScript: (ScriptID 201 4) self)
			)
			(5
				(self setScript: (ScriptID 209 0) self 209)
			)
			(6
				(messager say: 19 0 18 0 self 202)
			)
			(7
				(self setScript: (ScriptID 209 1) self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAlienHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1 (proc201_6 self))
			(2 (= cycles 2))
			(3
				(messager say: 15 0 0 1 self 202)
			)
			(4
				(self setScript: (ScriptID 201 4) self 0)
			)
			(5
				(self setScript: (ScriptID 209 0) self 208)
			)
			(6
				((ScriptID 209 2) setCycle: End self)
			)
			(7
				((ScriptID 209 2) setCel: 0)
				(= cycles 1)
			)
			(8
				(messager say: 15 0 0 2 self 202)
			)
			(9 (= cycles 1))
			(10
				(curRoom newRoom: 206)
				(eureka state: 1)
				(self dispose:)
			)
		)
	)
)

(instance sAfterAlien of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 232)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 209 2) init: view: 218 setLoop: 0 cel: 0)
				(= seconds 2)
			)
			(1
				((ScriptID 209 2) setCycle: Fwd self)
				(= seconds 2)
			)
			(2
				(self setScript: (ScriptID 209 1) self)
				(proc201_5 0)
			)
			(3
				(messager say: 15 0 0 3 self 202)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
