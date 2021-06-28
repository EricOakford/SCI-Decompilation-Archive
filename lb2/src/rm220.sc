;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh) (include "220.shm")
(use Main)
(use LBRoom)
(use Inset)
(use Conv)
(use Talker)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm220 0
	Laura220 2
	Sam220 3
)

(local
	conv1 = [
		-1 N_CARTOON NULL NULL 1 0 0 0
		-1 N_CARTOON NULL NULL 2 0 0 0
		-1 N_CARTOON NULL NULL 3 0 0 0
		-1 N_CARTOON NULL NULL 4 0 0 0
		-1 N_CARTOON NULL NULL 5 0 0 0
		-1 N_CARTOON NULL NULL 6 0 0 0
		-1 N_CARTOON NULL NULL 7 0 0 0
		-1 N_CARTOON NULL NULL 8
		]
	conv2 = [
		-1 N_CARTOON NULL NULL 9 0 0 0
		-1 N_CARTOON NULL NULL 10 0 0 0
		-1 N_CARTOON NULL NULL 11 0 0 0
		-1 N_CARTOON NULL NULL 12 0 0 0
		-1 N_CARTOON NULL NULL 13 0 0 0
		-1 N_CARTOON NULL NULL 14
		]
)
(instance rm220 of LBRoom
	(properties
		picture 220
	)
	
	(method (init)
		(LoadMany RES_VIEW 221 220 1220 1221)
		(Load RES_SOUND 220)
		(Load RES_PIC 225)
		(self setRegions: 92)
		(super init:)
		(WrapMusic dispose:)
		(theMusic number: 220 loop: -1 flags: mNOPAUSE play:)
		(fan init: setCycle: Forward)
		(shadowL init: setScript: sLeftShadow)
		(shadowR init: setScript: sRightShadow)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(ego get: -1 iNotebook)
				(myConv load: @conv1 init: self)
			)
			(2
				(curRoom setInset: inLauraPeeks)
				(= seconds 5)
			)
			(3
				(ego hide:)
				(curRoom setInset: 0)
				(= cycles 1)
			)
			(4
				(myConv load: @conv2 init: self)
			)
			(5 (theMusic fade: self))
			(6 (curRoom newRoom: 26))
		)
	)
)

(instance sRightShadow of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(shadowR setLoop: 1 setMotion: MoveTo 180 84 self)
			)
			(1
				(= seconds (Random 3 6))
			)
			(2
				(shadowR posn: -20 89)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sLeftShadow of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(shadowL setLoop: 0 setMotion: MoveTo -30 89 self)
			)
			(2
				(shadowL posn: 175 85)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance shadowL of Actor
	(properties
		x 175
		y 84
		view 221
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance shadowR of Actor
	(properties
		x -20
		y 89
		view 221
		loop 1
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance fan of Prop
	(properties
		x 10
		y 177
		view 220
	)
)

(instance inLauraPeeks of Inset
	(properties
		picture 225
		hideTheCast TRUE
	)
)

(instance myConv of Conversation)

(instance Sam220 of Talker
	(properties
		x 0
		y 0
		view 1220
		loop 3
		disposeWhenDone FALSE
		talkWidth 200
		modeless TRUE
		back 15
		textX 10
		textY 10
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 samEyes samMouth &rest)
	)
)

(instance samMouth of Prop
	(properties
		nsTop 63
		nsLeft 247
		view 1220
	)
)

(instance samEyes of Prop
	(properties
		nsTop 58
		nsLeft 251
		view 1220
		loop 2
	)
)

(instance Laura220 of Talker
	(properties
		x 0
		y 0
		view 1221
		loop 3
		priority 14
		signal fixPriOn
		disposeWhenDone 0
		talkWidth 150
		modeless TRUE
		back 15
		textX 139
		textY 104
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 0 lMouth &rest)
	)
)

(instance lMouth of Prop
	(properties
		nsTop 98
		nsLeft 117
		view 1221
		priority 7
		signal fixPriOn
	)
)
