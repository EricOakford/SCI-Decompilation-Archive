;;; Sierra Script 1.0 - (do not remove this comment)
(script# 223)
(include sci.sh)
(use Main)
(use Scaler)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	slaveWomenScr 0
)

(local
	[local0 4]
	local4
)
(instance slave1 of Actor
	(properties
		x 137
		y 124
		noun 7
		view 225
		signal $4800
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance slave2 of Actor
	(properties
		x 152
		y 132
		noun 7
		view 225
		loop 1
		signal $4800
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance slave3 of Actor
	(properties
		x 165
		y 143
		noun 7
		view 225
		loop 3
		signal $4800
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance slave4 of Actor
	(properties
		x 179
		y 151
		noun 7
		view 225
		loop 2
		signal $4800
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk)
	)
)

(instance slaveWomenScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 223)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local4 (if (== currentAct 3) 3 else 4))
				(= [local0 0] (slave1 init: yourself:))
				(= [local0 1] (slave2 init: yourself:))
				(= [local0 2] (slave3 init: yourself:))
				(= [local0 3] (slave4 init: yourself:))
				(= cycles 3)
			)
			(1
				(messager say: 1 0 local4 1 self)
			)
			(2
				(= temp0 0)
				(while (< temp0 4)
					([local0 temp0]
						setMotion:
							MoveTo
							(- ([local0 temp0] x?) 19)
							(- ([local0 temp0] y?) 11)
							(if (== temp0 3) self else 0)
					)
					(++ temp0)
				)
			)
			(3
				(if (== currentAct 5)
					(messager say: 1 0 local4 2 self)
				else
					(self cue:)
				)
			)
			(4
				(if (== currentAct 3)
					((ScriptID 220 4)
						view: 725
						setLoop: 1
						setCycle: Rev
						setMotion:
							MoveTo
							(+ ((ScriptID 220 4) x?) 10)
							((ScriptID 220 4) y?)
							self
					)
				else
					(self cue:)
				)
			)
			(5
				(if (== currentAct 3)
					((ScriptID 220 4) setCycle: 0 setLoop: -1)
					(self setScript: (ScriptID 220 1) self 1)
				else
					(self setScript: (ScriptID 220 7) self)
				)
			)
			(6
				((ScriptID 220 3) setPri: 7)
				((ScriptID 220 4) setPri: 4)
				(= temp0 0)
				(while (< temp0 4)
					([local0 temp0] setPri: 5)
					(++ temp0)
				)
				(messager
					say: 1 0 local4 (if (== currentAct 3) 2 else 3) self
				)
			)
			(7
				(messager
					say: 1 0 local4 (if (== currentAct 3) 3 else 4) self
				)
			)
			(8
				((ScriptID 220 4) stopUpd:)
				([local0 0] setMotion: MoveTo 104 94 self)
				([local0 1]
					setMotion: MoveTo ([local0 0] x?) ([local0 0] y?) self
				)
				([local0 2]
					setMotion: MoveTo ([local0 1] x?) ([local0 1] y?) self
				)
				([local0 3]
					setMotion: MoveTo ([local0 2] x?) ([local0 2] y?) self
				)
			)
			(9 0)
			(10 0)
			(11 0)
			(12
				([local0 0]
					setPri: 2
					setScale: Scaler 64 94 103 95
					setMotion: MoveTo 75 100 [local0 0]
				)
				([local0 1] setMotion: MoveTo 104 94 self)
				([local0 2]
					setMotion: MoveTo ([local0 1] x?) ([local0 1] y?) self
				)
				([local0 3]
					setMotion: MoveTo ([local0 2] x?) ([local0 2] y?) self
				)
			)
			(13 0)
			(14 0)
			(15
				([local0 1]
					setPri: 2
					setScale: Scaler 64 94 103 95
					setMotion: MoveTo 75 100 [local0 1]
				)
				([local0 2] setMotion: MoveTo 104 94 self)
				([local0 3]
					setMotion: MoveTo ([local0 2] x?) ([local0 2] y?) self
				)
			)
			(16 0)
			(17
				([local0 2]
					setPri: 2
					setScale: Scaler 64 94 103 95
					setMotion: MoveTo 75 100 [local0 2]
				)
				([local0 3] setMotion: MoveTo 104 94 self)
			)
			(18
				([local0 3]
					setPri: 2
					setScale: Scaler 64 94 103 95
					setMotion: MoveTo 75 100 self
				)
			)
			(19
				([local0 3] dispose:)
				(= cycles 2)
			)
			(20
				(if (== currentAct 3)
					((ScriptID 220 4)
						setCycle: Walk
						setMotion: MoveTo (- ((ScriptID 220 4) x?) 10) ((ScriptID 220 4) y?)
					)
				)
				((ScriptID 220 3) setPri: -1)
				((ScriptID 220 4) setPri: -1)
				(script cue:)
			)
			(21
				(if (== currentAct 3)
					((ScriptID 220 4)
						view: 224
						setLoop: -1
						loop: 1
						cel: 0
						posn: 139 109
					)
					(= cycles 2)
				else
					(self cue:)
				)
			)
			(22
				(if (== currentAct 3)
					(messager say: 1 0 local4 4 self)
				else
					(self cue:)
				)
			)
			(23
				(messager say: 1 0 local4 5 self)
			)
			(24
				((ScriptID 220 3) stopUpd:)
				((ScriptID 220 4) stopUpd:)
				((ScriptID 220 5) stopUpd:)
				(self dispose:)
			)
		)
	)
)
