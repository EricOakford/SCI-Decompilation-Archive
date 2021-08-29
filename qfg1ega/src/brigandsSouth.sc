;;; Sierra Script 1.0 - (do not remove this comment)
(script# 157)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	brigandsSouth 0
)

(instance brigandsSouth of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 157)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 95 9)
					show:
					posn: 122 222
					setPri: 15
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 195 194 self
				)
			)
			(1
				((ScriptID 95 9) setMotion: MoveTo 295 194 self)
				((ScriptID 95 11)
					init:
					posn: 286 224
					setPri: 15
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 286 190
				)
			)
			(2
				(if (ego inRect: 220 150 320 200)
					(self changeState: 9)
				else
					((ScriptID 95 9) loop: 3)
					((ScriptID 95 10)
						init:
						posn: 340 194
						setPri: 15
						illegalBits: 0
						ignoreActors: TRUE
						setCycle: Walk
						setMotion: MoveTo 304 194 self
					)
				)
			)
			(3
				((ScriptID 95 10) loop: 3)
				(= cycles 4)
			)
			(4
				(if ((ScriptID 95 0) notify: 3)
					((ScriptID 95 11) loop: 0)
					((ScriptID 95 10) loop: 1)
					(= cycles 18)
				else
					((ScriptID 95 0) notify: 5)
					((ScriptID 95 0) notify: 10)
					((ScriptID 95 9) setPri: 14 setMotion: MoveTo 304 165)
					((ScriptID 95 10) setPri: 14 setMotion: MoveTo 310 163)
					((ScriptID 95 11) setPri: 14 setMotion: MoveTo 307 161)
					(if (ego inRect: 50 108 230 124)
						(HandsOff)
						(ego loop: 0)
						(self changeState: 7)
					else
						(self changeState: 9)
					)
				)
			)
			(5
				((ScriptID 95 9) setMotion: MoveTo 340 194)
				((ScriptID 95 10) setMotion: MoveTo 340 194)
				((ScriptID 95 11) setMotion: MoveTo 307 250 self)
			)
			(6
				((ScriptID 95 0) notify: 10)
				((ScriptID 95 10) dispose:)
				((ScriptID 95 11) dispose:)
				(self dispose:)
			)
			(7
				(ego
					view: vEgoBigGrin
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 50 110
				)
				(= cycles 2)
			)
			(8
				((ScriptID 95 9)
					setPri: 13
					ignoreActors: FALSE
					setMotion: MoveTo 280 165
				)
				((ScriptID 95 10)
					ignoreActors: FALSE
					setMotion: MoveTo 310 150
				)
				(= cycles 20)
			)
			(9
				(EgoDead 157 0
					#icon vEgoDefeatedMagic 0 9
					#title {Don't you feel a draft?})
					;You'd better find a way to prevent the brigands from getting at you from all sides if you want to survive this.
			)
		)
	)
)
