;;; Sierra Script 1.0 - (do not remove this comment)
(script# 156)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	brigandsWest 0
	chandFall 1
)

(instance brigandsWest of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 156)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				((ScriptID 95 2) setCel: 1)
				((ScriptID 95 15)
					setPri: 3
					posn: 31 90
					ignoreActors: TRUE
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 211 90
				)
				(= cycles 4)
			)
			(2
				((ScriptID 95 17)
					setPri: 3
					ignoreActors:
					illegalBits: 0
					posn: 31 90
					setCycle: Walk
					setMotion: MoveTo 175 90
				)
				((ScriptID 95 18)
					ignoreActors: TRUE
					setLoop: 6
				)
				(= cycles 3)
			)
			(3
				((ScriptID 95 19)
					setPri: 3
					ignoreActors:
					illegalBits: 0
					posn: 31 90
					setCycle: Walk
					setMotion: MoveTo 160 90
				)
				((ScriptID 95 20)
					ignoreActors: TRUE
					setLoop: 5
				)
				(= cycles 5)
			)
			(4
				((ScriptID 95 2) setCel: 0)
				(= cycles 45)
			)
			(5
				(if ((ScriptID 95 0) notify: 1)
					(client setScript: (ScriptID 95 23))
				else
					(EgoDead 156 0
						#icon vEgoDefeatedMagic 0 9
						#title {Those guys look familiar.})
						;You're hopelessly surrounded. 
						; You should have stopped those brigands somehow. 
						; Maybe you'll see the light.
				)
			)
		)
	)
)

(instance chandFall of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 156)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(= cycles 18)
			)
			(1
				((ScriptID 95 8) dispose:)
				((ScriptID 95 6) show: posn: 154 32)
				((ScriptID 95 4)
					view: vBrigandTrio
					setLoop: 3
					setCel: 0
					setPri: 7
					posn: 153 62
				)
				(= cycles 1)
			)
			(2
				((ScriptID 95 6) posn: 152 47)
				((ScriptID 95 4) setCel: 1 posn: 151 77)
				(= cycles 1)
			)
			(3
				((ScriptID 95 6) posn: 150 62)
				((ScriptID 95 4) setCel: 2 posn: 149 92)
				(= cycles 1)
			)
			(4
				((ScriptID 95 4) dispose:)
				((ScriptID 95 6) dispose:)
				((ScriptID 95 19) ignoreActors: FALSE setCel: 1)
				((ScriptID 95 22)
					init:
					setPri: 10
					ignoreActors: TRUE
					posn: 147 100
					setCycle: Forward
				)
				((ScriptID 95 9) setScript: (ScriptID 95 21))
				(self dispose:)
			)
		)
	)
)
