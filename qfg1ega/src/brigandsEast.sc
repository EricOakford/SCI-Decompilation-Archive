;;; Sierra Script 1.0 - (do not remove this comment)
(script# 159)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	pushChair 0
	pushCan 1
	brigandsEast 2
)

(instance pushChair of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 159)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(if ((ScriptID 95 0) notify: 6)
					(ego setMotion: MoveTo 276 106 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: MoveTo 303 106 self)
			)
			(2 (ego loop: 3) (= cycles 1))
			(3
				(HighPrint 159 0)
				;You move the chair in front of the door.
				(ego setPri: 7 setMotion: MoveTo 300 102 self)
				((ScriptID 95 7) setCel: 0 posn: 297 100)
			)
			(4
				(ego setMotion: MoveTo 297 98 self)
				((ScriptID 95 7) posn: 288 96)
			)
			(5
				(ego setMotion: MoveTo 300 110 self)
			)
			(6
				(NormalEgo)
				(if ((ScriptID 95 0) notify: 3)
					(ego illegalBits: (| cWHITE cLRED))
				)
				((ScriptID 95 0) notify: 5)
				((ScriptID 95 0) notify: 7)
				((ScriptID 95 12) dispose:)
				((ScriptID 95 13) dispose:)
				((ScriptID 95 14) dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pushCan of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 159)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if ((ScriptID 95 0) notify: 6)
					(ego illegalBits: 0 setMotion: MoveTo 270 100 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setPri: 6 setMotion: MoveTo 274 92 self)
			)
			(2
				(ego illegalBits: 0 loop: 1)
				(= cycles 1)
			)
			(3
				(ego
					view: vEgoThrowing
					setLoop: 1
					setCel: 0
					setPri: 6
					posn: 272 91
				)
				(= cycles 1)
			)
			(4
				(ego setCel: 1 posn: 271 91)
				(= cycles 1)
			)
			(5
				(ego setCel: 2 posn: 267 91)
				(= cycles 1)
			)
			(6
				(ego setCel: 1 posn: 261 91)
				((ScriptID 95 5) setCel: 1)
				(= cycles 1)
			)
			(7
				(ego setCel: 0 posn: 256 91)
				((ScriptID 95 5) setCel: 2)
				(= cycles 1)
			)
			(8
				(ego view: vEgoStanding setLoop: 1 setCel: 0)
				((ScriptID 95 5) setCel: 3)
				(= cycles 1)
			)
			(9
				((ScriptID 95 5) setCel: 4)
				(NormalEgo)
				(if ((ScriptID 95 0) notify: 3)
					(ego illegalBits: (| cWHITE cLRED))
				)
				(= cycles 1)
			)
			(10
				((ScriptID 95 0) notify: 2)
				((ScriptID 95 0) notify: 7)
				(if ((ScriptID 95 0) notify: 4)
					(HandsOn)
					(self dispose:)
				else
					((ScriptID 95 12) setScript: (ScriptID 159 2))
				)
			)
		)
	)
)

(instance brigandsEast of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 159)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 95 0) notify: 11)
				((ScriptID 95 3) setPri: 4 setCel: 1)
				((ScriptID 95 12)
					show:
					setPri: 5
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Walk
					posn: 300 90
					setMotion: MoveTo 295 90 self
				)
			)
			(1
				((ScriptID 95 12) setMotion: MoveTo 282 100)
				((ScriptID 95 13)
					init:
					setPri: 5
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Walk
					posn: 310 90
					setMotion: MoveTo 294 90 self
				)
			)
			(2
				(if (ego inRect: 50 108 230 124)
					(HandsOff)
					(ego loop: 0)
					((ScriptID 95 12) setPri: -1 setMotion: MoveTo 224 113)
					((ScriptID 95 13) setMotion: MoveTo 277 114 self)
					((ScriptID 95 14)
						init:
						setPri: 5
						illegalBits: 0
						ignoreActors: TRUE
						setCycle: Walk
						posn: 310 90
						setMotion: MoveTo 294 90
					)
				else
					(self changeState: 5)
				)
			)
			(3
				(ego
					view: vEgoBigGrin
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 50 110
				)
				((ScriptID 95 12)
					ignoreActors: FALSE
					setMotion: MoveTo 204 113
				)
				((ScriptID 95 13)
					setPri: -1
					ignoreActors: FALSE
					setMotion: MoveTo 268 111
				)
				((ScriptID 95 14)
					ignoreActors: FALSE
					setMotion: MoveTo 287 102 self
				)
			)
			(4
				((ScriptID 95 14)
					setPri: -1
					setMotion: MoveTo 253 104 self
				)
			)
			(5
				(EgoDead 159 1
					#icon vEgoDefeatedMagic 0 9
					#title {You're half right but completely dead.})
					;There are still too many brigands for you to fight in here. 
					; You need to find some way to block one of the doors.
			)
		)
	)
)
