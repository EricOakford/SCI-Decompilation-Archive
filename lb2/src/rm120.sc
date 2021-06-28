;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh) (include "120.shm")
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm120 0
)

(instance rm120 of LBRoom
	(properties
		picture 120
		style FADEOUT
	)
	
	(method (init)
		(LoadMany RES_VIEW 131 121 130)
		(LoadMany RES_SOUND 120 121)
		(self setRegions: 92)
		(super init:)
		(person1 init: setScale: 122)
		(person2 init: setScale: 122)
		(theMusic number: 120 flags: mNOPAUSE loop: -1 play:)
		(curRoom setScript: sIntroScript)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance moveIt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks register)
				(= register 0)
			)
			(1
				(client setMotion: MoveTo 194 127 self)
			)
			(2
				(client loop: (Random 0 4) posn: 169 121)
				(= ticks (Random 12 60))
				(++ register)
			)
			(3
				(if (< register 3)
					(self changeState: 1)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sIntroScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
				(person1 setScale:)
				(person2 setScale:)
			)
			(1
				(person1 setScript: (moveIt new:) self 60)
				(person2 setScript: (moveIt new:) self 90)
			)
			(2 0)
			(3
				(person1 dispose:)
				(person2 dispose:)
				(= cycles 2)
			)
			(4
				(closeUpView init:)
				(gangplankBG init:)
				(= cycles 1)
			)
			(5
				(curRoom setScript: sCloseUp)
				(self dispose:)
			)
		)
	)
)

(instance sCloseUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(closeUpView stopUpd:)
				(person1
					init:
					view: 131
					posn: 78 82
					xStep: 5
					yStep: 2
					setPri: 8
					loop: 3
					moveSpeed: 16
					setCycle: Walk
				)
				(person2
					init:
					view: 131
					posn: 48 72
					xStep: 5
					yStep: 2
					setPri: 8
					loop: 2
					moveSpeed: 16
					setCycle: Walk
				)
				((ScriptID 1882 10)
					modeless: TRUE
					disposeWhenDone: FALSE
					x: 224
					y: 21
					textX: -214
					textY: 110
					talkWidth: 284
				)
				((ScriptID 1883 9)
					modeless: TRUE
					disposeWhenDone: FALSE
					x: 3
					y: 2
					textX: 7
					textY: 128
					talkWidth: 284
				)
				(self setScript: sDisembark self 1)
			)
			(1
				((ScriptID 1887 12)
					modeless: TRUE
					disposeWhenDone: FALSE
					x: 224
					y: 21
					textX: -214
					textY: 121
					talkWidth: 284
				)
				((ScriptID 1886 11)
					modeless: TRUE
					disposeWhenDone: FALSE
					x: 4
					y: 22
					textX: 7
					textY: 120
					talkWidth: 284
				)
				(person1
					init:
					view: 131
					posn: 78 82
					xStep: 5
					yStep: 2
					setPri: 8
					loop: 0
					moveSpeed: 16
					setCycle: Walk
				)
				(person2
					init:
					view: 131
					posn: 48 72
					xStep: 5
					yStep: 2
					setPri: 8
					loop: 1
					moveSpeed: 16
					setCycle: Walk
				)
				(= cycles 2)
			)
			(2
				(self setScript: sDisembark self 0)
			)
			(3
				(curRoom newRoom: 140)
				(self dispose:)
			)
		)
	)
)

(instance sDisembark of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if register
					(person1 setMotion: MoveTo 168 110)
				else
					(person1 setMotion: MoveTo 159 114)
				)
				(person2 setMotion: MoveTo 138 100 self)
			)
			(2
				(if register
					(person1 loop: 4 setCycle: EndLoop self)
					(person2 setCycle: 0)
				else
					(= cycles 2)
				)
			)
			(3
				(theMusic setVol: 40)
				(messager say: (if register N_DISEMBARK else N_TRUNK) NULL NULL 0 self)
			)
			(4
				(theMusic setVol: 127)
				(if register
					(person1 setCycle: BegLoop self)
				else
					(++ state)
					(= cycles 2)
				)
			)
			(5
				(person1 loop: 3 setCycle: Walk)
				(= cycles 2)
			)
			(6
				(person1 setMotion: MoveTo 211 134 self)
				(if (not register)
					(person2 setMotion: MoveTo 181 118 self)
				else
					(= cycles 2)
				)
			)
			(7 0)
			(8
				(person1 dispose:)
				(person2 setCycle: Walk setMotion: MoveTo 217 128 self)
			)
			(9 (self dispose:))
		)
	)
)

(instance person1 of Actor
	(properties
		x 169
		y 121
		view 121
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		moveSpeed 10
	)
)

(instance person2 of Actor
	(properties
		x 169
		y 121
		view 121
		loop 1
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		moveSpeed 10
	)
)

(instance gangplankBG of View
	(properties
		x 107
		y 42
		view 130
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance closeUpView of View
	(properties
		x 85
		y 25
		view 130
		cel 1
		priority 9
		signal (| ignrAct fixPriOn)
	)
)
