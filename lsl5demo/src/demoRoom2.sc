;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom2 0
)

(local
	i
	theX
	[str 50]
	[typeBits 11]
	local63
	local64
)
(procedure (Measure &tmp [sizeRect 2] strAddr temp3)
	(= strAddr (StrAt @str i))
	(Format @sizeRect {%c} strAddr)
	(= temp3
		(Display @sizeRect
			p_at theX 160
			p_color myTextColor6
			p_width 7
			p_mode teJustLeft
			p_font 2407
			p_save
		)
	)
	(if (and (< 21 i) (< i 29))
		(= [typeBits (- i 22)] temp3)
	else
		(UnLoad MEMORY temp3)
	)
	(= theX (+ theX 7))
	(if (== 32 strAddr) (= theX (- theX 2)))
)

(instance demoRoom2 of Room
	(properties
		picture pTitle
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE pTitle pBlack)
		(LoadMany VIEW pTitle)
		(LoadMany SOUND 903 905)
		(StrCpy @str
			{Passionate Patti Does Pittsbua Little Undercover Work}
		)
		(super init:)
		(light init:)
		(wood init:)
		(larry init: setCel: 0 stopUpd: setScript: sPasteThatBig5)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(super doit:)
		(if local64
			(Palette PALCycle 24 31 -1)
			(Palette PALCycle 240 254 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 1 loop: -1 playBed:)
				(DoDisplay {No, even worse, girls!__It's}
					#at -1 1
					#color myTextColor6
					#font 2510
					#mode teJustCenter
				)
				(= cycles 20)
			)
			(1
				(sPasteThatBig5 changeState: 1)
			)
			(2
				(DoDisplay {Back for Episode 4,}
					#at -1 155
					#color myTextColor6
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay {which he calls Larry 5!}
					#at -1 170
					#color myTextColor6
					#font 2510
					#mode teJustCenter
				)
			)
			(3
				(curRoom drawPic: (curRoom picture?))
				(= local64 1)
				(= i 0)
				(= theX 3)
				(= speed 3)
				(= cycles 20)
			)
			(4
				(Measure)
				(typeFwd play:)
				(if (< (++ i) 29)
					(= cycles (Random 2 3))
					(-- state)
				else
					(= cycles 5)
				)
			)
			(5
				(Display {}
					p_restore [typeBits (- (-- i) 22)]
				)
				(typeBack play:)
				(if (> i 22)
					(= cycles 2)
					(-- state)
				else
					(= theX 153)
					(= i 29)
					(= cycles 5)
				)
			)
			(6
				(Measure)
				(typeFwd play:)
				(if (< (++ i) (StrLen @str))
					(-- state)
					(= cycles (Random 2 3))
				else
					(= cycles 5)
				)
			)
			(7
				(light setCel: (not (light cel?)))
				(if (< (++ local63) 6) (-- state))
				(= cycles (Random 1 8))
			)
			(8
				(= speed 6)
				(UnLoad PICTURE pTitle)
				(UnLoad PICTURE pBlack)
				(curRoom newRoom: 3)
			)
		)
	)
)

(instance light of Prop
	(properties
		x 63
		y 18
		view pTitle
	)
)

(instance wood of View
	(properties
		x 223
		y 108
		view 105
		loop 4
		priority 2
		signal (| ignrAct skipCheck fixedLoop fixPriOn) ;$5810
	)
)

(instance larry of Prop
	(properties
		x 229
		y 132
		view 105
		cycleSpeed 1
	)
)

(instance sPasteThatBig5 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1 (larry setCycle: EndLoop self))
			(2
				(wood hide:)
				(larry setCel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(larry setCel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(4
				(sCartoon cue:)
				(= cycles 30)
			)
			(5
				(sCartoon cue:)
				(larry setCel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(6
				(sCartoon cue:)
				(= cycles 30)
			)
			(7 (larry setCycle: BegLoop self))
			(8 (larry stopUpd:))
		)
	)
)

(instance typeFwd of Sound
	(properties
		number 903
	)
)

(instance typeBack of Sound
	(properties
		number 905
	)
)
