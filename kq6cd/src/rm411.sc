;;; Sierra Script 1.0 - (do not remove this comment)
(script# 411)
(include sci.sh)
(use Main)
(use rLab)
(use n401)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	rm411 0
)

(local
	local0
)
(instance rm411 of LabRoom
	(properties
		north 400
		east 400
		south 400
		west 400
	)
	
	(method (init)
		(super init:)
		(if (Btst 1)
			(switch ((ScriptID 30 0) prevEdgeHit?)
				(1 (proc401_1))
				(3 (proc401_0))
				(4 (proc401_3))
				(2 (proc401_2))
			)
			(curRoom setScript: (ScriptID 30 1))
		else
			(theMusic number: 409 setLoop: -1 play:)
			(westHalfTrapFloor init: stopUpd:)
			(eastHalfTrapFloor init: stopUpd:)
			(eastTrapLedge init: stopUpd:)
			(westTrapLedge init: stopUpd:)
			(northTrapLedge init: stopUpd:)
			(curRoom setScript: dieAlready)
		)
		(switch ((ScriptID 30 0) prevEdgeHit?)
			(1
				((ScriptID 30 0) initCrypt: 1)
			)
			(3
				((ScriptID 30 0) initCrypt: 4)
				((ScriptID 30 7) addToPic:)
				((ScriptID 30 8) addToPic:)
			)
			(4
				((ScriptID 30 0) initCrypt: 4)
				((ScriptID 30 6) addToPic:)
				((ScriptID 30 10) addToPic:)
				((ScriptID 30 8) addToPic:)
			)
			(2
				((ScriptID 30 0) initCrypt: 2)
				((ScriptID 30 5) addToPic:)
				((ScriptID 30 9) addToPic:)
				((ScriptID 30 8) addToPic:)
			)
		)
	)
)

(instance dieAlready of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable:)
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(4
						(ego posn: 282 164 loop: 1 init:)
					)
					(2
						(ego posn: 36 164 loop: 0 init:)
					)
					(3
						(ego posn: 158 135 loop: 2 init:)
					)
					(1
						(ego posn: 158 225 loop: 3 init:)
					)
				)
				(= seconds 2)
			)
			(1
				(if
					(and
						(== prevRoomNum 425)
						(not (Btst 1))
						(< ((ScriptID 30 0) timesGenieHasAppeared?) 4)
					)
					(self cue:)
				else
					(messager say: 1 0 58 1 self 400)
				)
			)
			(2
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(4
						(ego setMotion: PolyPath 215 164 self)
					)
					(2
						(ego setMotion: PolyPath 108 164 self)
					)
					(3
						(ego setMotion: PolyPath 158 156 self)
					)
					(1
						(ego setMotion: PolyPath 158 185 self)
					)
				)
			)
			(3
				(if
					(and
						(== prevRoomNum 425)
						(not (Btst 1))
						(< ((ScriptID 30 0) timesGenieHasAppeared?) 4)
					)
					(= local0 1)
					(messager say: 1 0 3 1 self 400)
				else
					(self cue:)
				)
			)
			(4
				(switch ((ScriptID 30 0) prevEdgeHit?)
					(1
						(= temp0 3)
						(= temp1 0)
						(= temp2 0)
					)
					(3
						(= temp0 2)
						(= temp1 0)
						(= temp2 0)
					)
					(4
						(= temp0 1)
						(= temp1 -5)
						(= temp2 0)
					)
					(2
						(= temp0 0)
						(= temp1 7)
						(= temp2 2)
					)
				)
				(ego
					x: (+ (ego x?) temp1)
					y: (+ (ego y?) temp2)
					view: 4011
					normal: 0
					cycleSpeed: 6
					setLoop: temp0
					setCycle: CycleTo 10 1 self
				)
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(if local0
					(Bset 59)
					(messager say: 1 0 3 2 self 400)
				else
					(messager say: 1 0 58 2 self 400)
				)
			)
			(7
				(theMusic number: 432 setLoop: 1 play: self)
			)
			(8
				(Bclr 59)
				(theMusic number: 307 setLoop: 1 play:)
				(ShakeScreen 2 2)
				(= ticks 4)
			)
			(9
				(curRoom drawPic: 98 100)
				(cast eachElementDo: #hide)
				(= cycles 4)
			)
			(10 (EgoDead 7))
		)
	)
)

(instance westHalfTrapFloor of View
	(properties
		x 83
		y 159
		view 409
		priority 1
		signal $4010
	)
)

(instance eastHalfTrapFloor of View
	(properties
		x 233
		y 158
		view 409
		loop 1
		priority 1
		signal $4010
	)
)

(instance northTrapLedge of View
	(properties
		x 108
		y 148
		view 409
		loop 4
		priority 2
		signal $4010
	)
)

(instance eastTrapLedge of View
	(properties
		x 211
		y 141
		view 409
		loop 2
		priority 3
		signal $4010
	)
)

(instance westTrapLedge of View
	(properties
		x 104
		y 140
		view 409
		loop 3
		priority 3
		signal $4010
	)
)
