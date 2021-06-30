;;; Sierra Script 1.0 - (do not remove this comment)
(script# 442)
(include game.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Motion)
(use System)

(public
	sOHMeeting 0
	sOHNoMeet 1
	sOHLeave 2
)

(local
	local0
)
(instance sOHMeeting of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				((ScriptID 32 0)
					init:
					view: 814
					loop: 1
					setPri: 9
					setScale: Scaler 155 0 190 90
					x: 225
					y: 138
					room: 440
				)
				(= cycles 1)
			)
			(2
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 189 145 self
				)
			)
			(3
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 127 154 self
				)
			)
			(4
				((ScriptID 90 2) setMotion: PolyPath 124 156 self)
				((ScriptID 32 0) setMotion: MoveTo 86 159 self)
			)
			(5 0)
			(6
				(Face (ScriptID 32 0) (ScriptID 90 2))
				(= cycles 5)
			)
			(7
				(messager say: 2 0 3 0 self 1440)
			)
			(8 (self dispose:))
		)
	)
)

(instance sOHNoMeet of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(ego setMotion: MoveTo 98 161 self)
			)
			(2
				((ScriptID 32 0)
					init:
					view: 814
					setPri: 9
					x: 225
					y: 138
					room: 440
				)
				(Face ego (ScriptID 32 0))
				(= cycles 1)
			)
			(3
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 189 145 self
				)
			)
			(4
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 127 154 self
				)
			)
			(5
				((ScriptID 90 2) setMotion: PolyPath 176 150 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance sOHLeave of Script

	(method (doit)
		(if
			(and
				(== (self state?) 3)
				(not ((ScriptID 90 2) mover?))
				(not ((ScriptID 32 0) mover?))
				(not local0)
			)
			(= local0 1)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				((ScriptID 90 2) setMotion: PolyPath 228 133 sOHLeave)
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 189 145 self
				)
			)
			(2
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 230 138 self
				)
			)
			(3 0)
			(4 (= seconds 1))
			(5
				((ScriptID 32 0) dispose:)
				(self dispose:)
			)
		)
	)
)
