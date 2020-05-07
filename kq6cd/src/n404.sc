;;; Sierra Script 1.0 - (do not remove this comment)
(script# 404)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	proc404_0 0
	proc404_1 1
	proc404_2 2
)

(local
	local0
	local1
	local2
	local3
)
(procedure (proc404_0 param1)
	(ego setScript: holeOnWall 0 param1)
)

(procedure (proc404_1)
	((ScriptID 30 0) holeIsUp: 1)
	(switch ((ScriptID 30 0) holeWall?)
		(2
			(aHole posn: 281 222 setPri: 13 init:)
		)
		(1
			(aHole posn: 159 191 setLoop: 7 setPri: 10 init:)
		)
		(4
			(aHole posn: 36 223 setPri: 13 init:)
		)
	)
)

(procedure (proc404_2)
	(if (cast contains: aHole) (aHole dispose: delete:))
	(DisposeScript 404)
)

(instance aHole of Actor
	(properties
		z 100
		noun 17
		modNum 400
		sightAngle 45
		view 232
		loop 6
		signal $6810
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: lookInHole))
			(2
				(messager say: 17 2 0 1 0 400)
			)
			(5 (ego setScript: getHole))
			(else 
				(messager say: 17 0 0 1 0 400)
			)
		)
	)
)

(instance holeInset of View
	(properties
		x 157
		y 105
		view 487
		priority 15
		signal $6010
	)
)

(instance wall1 of View
	(properties
		x 160
		y 139
		view 414
		priority 12
		signal $6010
	)
)

(instance wall2 of View
	(properties
		x 149
		y 139
		view 414
		priority 12
		signal $6010
	)
)

(instance wall3 of View
	(properties
		x 139
		y 139
		view 414
		priority 12
		signal $6010
	)
)

(instance wall4 of View
	(properties
		x 129
		y 139
		view 414
		priority 12
		signal $6010
	)
)

(instance sDoor of Prop
	(properties
		x 205
		y 96
		view 414
		loop 4
		priority 13
		signal $6010
	)
)

(instance dirt of Prop
	(properties
		x 159
		y 100
		view 490
		priority 13
		signal $6010
	)
)

(instance holeOnWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 18 curRoomNum)
				(theGame handsOff:)
				((ScriptID 30 0) holeIsUp: 1 holeWall: register)
				(if (== curRoomNum 400)
					((ScriptID 30 0)
						holeCoords: ((ScriptID 30 0) labCoords?)
					)
				else
					((ScriptID 30 0) holeCoords: curRoomNum)
				)
				(switch register
					(2
						(= local1 259)
						(= local2 173)
					)
					(1
						(= local1 157)
						(= local2 144)
					)
					(4
						(= local1 59)
						(= local2 173)
					)
				)
				(ego setMotion: PolyPath local1 local2 self)
			)
			(1
				(switch register
					(2 (ego setHeading: 45))
					(1 (ego setHeading: 0))
					(4 (ego setHeading: 270))
				)
				(= cycles 10)
			)
			(2
				(switch register
					(2
						(= local1 271)
						(= local2 173)
						(ego setLoop: 0)
					)
					(1
						(= local1 157)
						(= local2 146)
						(ego setLoop: 2)
					)
					(4
						(= local1 48)
						(= local2 173)
						(ego setLoop: 1)
					)
				)
				(ego
					view: 232
					normal: 0
					cel: 0
					posn: local1 local2
					cycleSpeed: 6
				)
				(if (== register 1)
					(ego setCycle: CT 4 1 self)
				else
					(ego setCycle: CT 5 1 self)
				)
			)
			(3
				(switch register
					(2
						(aHole posn: 281 222 setPri: 13 init:)
					)
					(1
						(aHole posn: 159 191 setLoop: 7 setPri: 10 init:)
					)
					(4
						(aHole posn: 36 223 setPri: 13 init:)
					)
				)
				(ego cel: 6)
				(= seconds 1)
			)
			(4
				(if (and (== curRoomNum 409) (> (aHole x?) 250))
					(messager say: 19 25 55 1 self 400)
				else
					(messager say: 6 25 0 1 self 400)
				)
			)
			(5
				(if
					(and
						(== curRoomNum 409)
						(not (Btst 1))
						(> (aHole x?) 250)
					)
					(self cue:)
				else
					(messager say: 6 25 0 2 self 400)
				)
			)
			(6
				(switch register
					(2 (ego posn: 259 173 reset: 6))
					(1 (ego posn: 157 144 reset: 3))
					(4 (ego posn: 59 173 reset: 1))
				)
				(= cycles 6)
			)
			(7
				(switch register
					(2
						(= local1 236)
						(= local2 173)
					)
					(1
						(= local1 157)
						(= local2 158)
					)
					(4
						(= local1 79)
						(= local2 173)
					)
				)
				(ego
					setLoop: (ego cel?)
					setMotion: MoveTo local1 local2 self
				)
			)
			(8 (= cycles 8))
			(9
				(if
					(and
						(== curRoomNum 409)
						(not (Btst 1))
						(> (aHole x?) 250)
					)
					(soundFx2 number: 483 setLoop: 1 play:)
					(aHole
						yStep: 6
						setCycle: Fwd
						setMotion: MoveTo (aHole x?) 5 self
					)
				else
					(self cue:)
				)
			)
			(10
				(if
					(and
						(== curRoomNum 409)
						(not (Btst 1))
						(> (aHole x?) 250)
					)
					(messager say: 19 25 55 2 self 400)
				else
					(self cue:)
				)
			)
			(11
				(if
					(and
						(== curRoomNum 409)
						(not (Btst 1))
						(> (aHole x?) 250)
					)
					(messager say: 19 25 55 3 self 400)
				else
					(self cue:)
				)
			)
			(12
				(theGame handsOn:)
				(ego setLoop: -1)
				(self dispose:)
			)
		)
	)
)

(instance lookInHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((< (aHole x?) 82) (= register 4) (= local1 58) (= local2 172))
					((< (aHole x?) 233) (= register 1) (= local1 157) (= local2 144))
					(else (= register 2) (= local1 259) (= local2 170))
				)
				(ego setMotion: PolyPath local1 local2 self)
			)
			(1
				(switch register
					(4 (ego setHeading: 270))
					(1 (ego setHeading: 0))
					(2 (ego setHeading: 90))
				)
				(= cycles 12)
			)
			(2
				(switch register
					(4
						(= local1 47)
						(= local2 172)
						(ego setPri: 14 setLoop: 4)
					)
					(1
						(= local1 157)
						(= local2 146)
						(ego setPri: 11 setLoop: 5)
					)
					(2
						(= local1 268)
						(= local2 171)
						(ego setPri: 14 setLoop: 3)
					)
				)
				(ego
					view: 232
					cel: 0
					normal: 0
					posn: local1 local2
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(3
				(messager say: 17 1 0 0 self 400)
			)
			(4 (ego setCycle: Beg self))
			(5
				(switch register
					(4 (ego posn: 58 172 reset: 1))
					(1 (ego posn: 157 144 reset: 3))
					(2 (ego posn: 259 170 reset: 0))
				)
				(= cycles 6)
			)
			(6
				(switch register
					(4
						(= local1 78)
						(= local2 172)
					)
					(1
						(= local1 157)
						(= local2 159)
					)
					(2
						(= local1 239)
						(= local2 170)
					)
				)
				(ego
					setLoop: (ego cel?)
					setMotion: PolyPath local1 local2 self
				)
			)
			(7
				(theGame handsOn:)
				(ego reset:)
				(self dispose:)
			)
		)
	)
)

(instance getHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((< (aHole x?) 82) (= register 4) (= local1 59) (= local2 173))
					((< (aHole x?) 233) (= register 1) (= local1 157) (= local2 144))
					(else (= register 2) (= local1 256) (= local2 173))
				)
				(ego setMotion: PolyPath local1 local2 self)
			)
			(1
				(switch register
					(4 (ego setHeading: 270))
					(1 (ego setHeading: 0))
					(2 (ego setHeading: 45))
				)
				(= cycles 10)
			)
			(2
				(messager say: 17 5 0 1 self 400)
			)
			(3
				(switch register
					(2
						(ego posn: 268 173 setLoop: 0)
					)
					(1
						(ego posn: 157 146 setLoop: 2)
					)
					(4
						(= local1 48)
						(= local2 173)
						(ego posn: 48 173 setLoop: 1)
					)
				)
				(ego view: 232 normal: 0 cel: 6)
				(= cycles 3)
			)
			(4
				(aHole dispose:)
				(ego cycleSpeed: 6 setCycle: Beg self)
			)
			(5
				(switch register
					(2 (ego posn: 259 170 reset: 6))
					(1 (ego posn: 157 144 reset: 3))
					(4 (ego posn: 58 172 reset: 1))
				)
				(= cycles 6)
			)
			(6
				(switch register
					(2
						(= local1 239)
						(= local2 170)
					)
					(1
						(= local1 157)
						(= local2 156)
					)
					(4
						(= local1 78)
						(= local2 172)
					)
				)
				(= ticks 6)
			)
			(7
				(ego
					setLoop: (ego cel?)
					setMotion: MoveTo local1 local2 self
				)
			)
			(8
				(theGame handsOn:)
				(ego setLoop: -1 get: 18)
				((ScriptID 30 0) holeCoords: 0)
				((ScriptID 30 0) holeWall: 0)
				(self dispose:)
			)
		)
	)
)
