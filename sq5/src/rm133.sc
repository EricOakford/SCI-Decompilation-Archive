;;; Sierra Script 1.0 - (do not remove this comment)
(script# 133)
(include game.sh) (include "133.shm")
(use Main)
(use Scaler)
(use RandCyc)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm133 0
)

(local
	underbits
	local1
	local2 =  4
	local3
	[str1 30]
	[str2 30]
)
(instance rm133 of Room
	(properties
		picture 31
		style (| BLACKOUT FADEOUT)
		horizon 50
		vanishingX 130
		vanishingY 50
	)
	
	(method (init)
		(theGame handsOff:)
		(theMusic1 number: 140 setLoop: -1 play:)
		(Palette PALCycle 32 34 1)
		(Palette PALCycle 36 38 1)
		(Palette PALCycle 44 46 1)
		(Palette PALCycle 40 42 1)
		(tech1 init: setCycle: Forward)
		(tech2 init: setCycle: Forward)
		(tech3 init: setCycle: Forward setScript: sDoLights)
		(super init:)
		(curRoom setScript: sDoAll)
	)
)

(instance sDoLights of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 2 8)))
			(1
				(Palette PALCycle 38 40 1)
				(Palette PALCycle 44 46 1)
				(Palette PALCycle 40 42 1)
				(= cycles (Random 2 8))
			)
			(2
				(Palette PALCycle 38 40 1)
				(Palette PALCycle 46 48 1)
				(Palette PALCycle 42 44 1)
				(= cycles (Random 2 8))
			)
			(3
				(Palette PALCycle 34 36 1)
				(Palette PALCycle 36 38 1)
				(= cycles (Random 2 8))
			)
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sDoAll of Script

	(method (changeState newState &tmp theX theY)
		(switch (= state newState)
			(0
				(extMouse init:)
				(= ticks 10)
			)
			(1
				(extMouse loop: 1 cel: 0 x: 181 y: 187)
				(= ticks 10)
			)
			(2
				(extMouse setCel: 1)
				(= ticks 10)
			)
			(3
				(extMouse setCel: 0)
				(= ticks 10)
			)
			(4
				(extMouse setCel: 1)
				(= ticks 10)
			)
			(5
				(extMouse setLoop: 2 setCel: 0 x: 183 y: 185)
				(= ticks 10)
			)
			(6
				(theMusic2 number: 141 setLoop: -1 play:)
				(extMouse
					view: 143
					setLoop: -1
					setLoop: 0
					cel: 0
					setStep: 5 6
					cycleSpeed: 2
					x: 169
					y: 183
					setCycle: Forward
					setMotion: MoveTo 169 134 self
				)
			)
			(7
				(theMusic2 number: 120 setLoop: 1 play:)
				(extMouse dispose:)
				(= cycles 2)
			)
			(8
				(= local3 6)
				(inbottom
					init:
					setScale: Scaler local3 local3 122 99
					scaleSignal: 1
				)
				(= cycles 1)
			)
			(9
				(= local3 (+ local3 local2))
				(inbottom setScale: Scaler local3 local3 122 99)
				(if (< local3 100) (= state (- state 1)))
				(= cycles 1)
			)
			(10
				(inbottom setScale: 0 scaleSignal: 0)
				(= cycles 1)
			)
			(11
				(tail init: setCycle: RandCycle)
				(= cycles 10)
			)
			(12
				(tail setLoop: 2 cel: 0)
				(= cycles 1)
			)
			(13 (tail setCycle: EndLoop self))
			(14
				(tail dispose:)
				(= cycles 1)
			)
			(15
				(inbottom
					setStep: 7 7
					setScale: 0
					setMotion: MoveTo 161 254 self
				)
				(intop init: setStep: 7 7 setMotion: MoveTo 161 146)
			)
			(16
				(inbottom stopUpd:)
				(intop stopUpd:)
				(= cycles 2)
			)
			(17
				(= underbits (Graph GSaveBits 93 179 99 187 1))
				(Message MsgGet 133 1 0 0 1 @str1)
				(Message MsgGet 133 1 0 0 2 @str2)
				(= theX (WhichLanguage 116 116 116 116 116))
				(= theY (WhichLanguage 74 74 74 74 74))
				(Display
					@str1
					p_at
					theX
					theY
					p_font
					1605
					p_color
					14
				)
				(= theX (WhichLanguage 163 136 136 136 136))
				(= theY (WhichLanguage 76 74 76 74 74))
				(Display @str2 p_at theX theY p_font 10 p_color 5)
				(= ticks 40)
			)
			(18
				(Message MsgGet 133 1 0 0 3 @str1)
				(Message MsgGet 133 1 0 0 11 @str2)
				(= theX (WhichLanguage 116 116 116 116 116))
				(= theY (WhichLanguage 84 84 84 84 84))
				(Display
					@str1
					p_at
					theX
					theY
					p_font
					1605
					p_color
					14
				)
				(= theX (WhichLanguage 163 186 163 186 186))
				(= theY (WhichLanguage 84 84 84 84 84))
				(Display
					@str2
					p_at
					theX
					theY
					p_font
					1605
					p_color
					14
				)
				(= ticks 40)
			)
			(19
				(Message MsgGet 133 1 0 0 4 @str1)
				(= theX (WhichLanguage 116 116 116 116 116))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str1 p_at theX theY p_font 10 p_color 5)
				(= local1 (Graph GSaveBits 90 163 100 211 1))
				(= ticks 40)
			)
			(20
				(Message MsgGet 133 1 0 0 5 @str1)
				(= theX (WhichLanguage 163 173 173 173 173))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(21
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(22
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(23
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(24
				(Graph GRestoreBits local1)
				(Graph GShowBits 90 163 100 211 1)
				(Message MsgGet 133 1 0 0 6 @str2)
				(= theX (WhichLanguage 163 186 163 186 186))
				(= theY (WhichLanguage 94 94 94 94 94))
				(Display @str2 p_at theX theY p_font 10 p_color 9)
				(= ticks 40)
			)
			(25
				(Message MsgGet 133 1 0 0 8 @str2)
				(= theX (WhichLanguage 116 116 116 116 116))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str2 p_at theX theY p_font 10 p_color 5)
				(= local1 (Graph GSaveBits 100 163 110 211 1))
				(= ticks 40)
			)
			(26
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(27
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(28
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(29
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(30
				(Graph GRestoreBits local1)
				(Graph GShowBits 100 163 110 211 1)
				(Message MsgGet 133 1 0 0 6 @str2)
				(= theX (WhichLanguage 163 186 163 186 186))
				(= theY (WhichLanguage 104 104 104 104 104))
				(Display @str2 p_at theX theY p_font 10 p_color 9)
				(= ticks 40)
			)
			(31
				(Message MsgGet 133 1 0 0 7 @str2)
				(= theX (WhichLanguage 116 116 116 116 116))
				(= theY (WhichLanguage 114 114 114 114 114))
				(Display @str2 p_at theX theY p_font 10 p_color 5)
				(= ticks 40)
			)
			(32
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 114 114 114 114 114))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(33
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 114 114 114 114 114))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(34
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 114 114 114 114 114))
				(Display @str1 p_at theX theY p_font 10 p_color 19)
				(= ticks 20)
			)
			(35
				(= theX (WhichLanguage 163 173 163 173 173))
				(= theY (WhichLanguage 114 114 114 114 114))
				(Display @str1 p_at theX theY p_font 10 p_color 28)
				(= ticks 20)
			)
			(36
				(inbottom dispose:)
				(intop dispose:)
				(= cycles 2)
			)
			(37
				(theMusic2 stop:)
				(curRoom newRoom: 132)
			)
		)
	)
)

(instance extMouse of Actor
	(properties
		x 181
		y 189
		view 141
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 100 11 189 129)
	)
)

(instance tail of Actor
	(properties
		x 178
		y 127
		view 142
		loop 1
		signal (| ignrAct ignrHrz)
		cycleSpeed 0
		moveSpeed 0
	)
)

(instance tech1 of Actor
	(properties
		x 113
		y 125
		view 141
		loop 3
		priority 5
		signal fixPriOn
		cycleSpeed 100
	)
)

(instance tech2 of Actor
	(properties
		x 219
		y 129
		view 141
		loop 4
		priority 5
		signal fixPriOn
		cycleSpeed 100
	)
)

(instance tech3 of Actor
	(properties
		x 309
		y 86
		view 141
		loop 5
		priority 5
		signal fixPriOn
		cycleSpeed 100
	)
)

(instance intop of Actor
	(properties
		x 161
		y 53
		view 142
		priority 6
		signal (| ignrAct ignrHrz fixPriOn)
		moveSpeed 0
	)
)

(instance inbottom of Actor
	(properties
		x 161
		y 146
		view 142
		cel 1
		priority 6
		signal (| ignrAct ignrHrz fixPriOn)
		moveSpeed 0
	)
)
