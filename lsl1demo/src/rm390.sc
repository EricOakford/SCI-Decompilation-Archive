;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	local0
	local1 =  7
	local2 =  12
)
(instance rm390 of Room
	(properties
		picture 160
		style IRISIN
	)
	
	(method (init)
		(fakeEgo init:)
		(super init:)
		(music number: 901 loop: 1 vol: 127 priority: 50 playBed:)
		(eve init:)
		(self setScript: sRoomScript)
	)
)

(instance sRoomScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (not (eve script?)) (< (fakeEgo x?) 200))
			(eve setScript: sEve)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fakeEgo setCycle: Walk setMotion: MoveTo 160 125 self)
				(SCIDisplay
					{"Leisure Suit Larry in the}
					(= local1 (+ local1 local2))
					#back myTextColor
				)
				(SCIDisplay
					{Land of the Lounge Lizards"}
					(= local1 (+ local1 local2))
					#back myTextColor
				)
				(= cycles 20)
			)
			(1
				(SCIDisplay
					{...more graphic(s) than ever before!}
					(+ local1 local2 8)
					#back myTextColor
				)
			)
		)
	)
)

(instance sEve of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== (music prevSignal?) 10) (= register 1))
		(if
			(and
				(== state 4)
				(or register (> (GetTime SYSTIME1) local0))
			)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eve setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(eve setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(fakeEgo loop: 3 cel: 0 x: 155 setCycle: EndLoop self)
			)
			(3
				(= local0 (+ (GetTime SYSTIME1) 7))
				(= cycles 1)
			)
			(4
				(UnLoad VIEW 150)
				(UnLoad PICTURE 150)
				(UnLoad SOUND 902)
				(UnLoad SOUND 132)
				(UnLoad SOUND 133)
				(UnLoad SOUND 130)
				(LoadMany PICTURE 170 180)
			)
			(5 (curRoom newRoom: 700))
		)
	)
)

(instance eve of Prop
	(properties
		x 137
		y 132
		view 160
		cycleSpeed 3
	)
)

(instance fakeEgo of Actor
	(properties
		x 249
		y 89
		view 160
		loop 2
		signal fixedLoop
	)
	
	(method (cue)
		(super cue:)
		(self loop: 3 cel: 0 x: 155)
	)
)
