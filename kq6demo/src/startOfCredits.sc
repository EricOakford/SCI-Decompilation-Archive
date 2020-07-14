;;; Sierra Script 1.0 - (do not remove this comment)
(script# 107)
(include game.sh)
(use Main)
(use Actor)
(use System)

(public
	startOfCredits 0
	openingCredits 1
	fadeThePic 2
)

(local
	[str1 200]
	[str2 200]
	saveBits1
	saveBits2
	local402
	[local403 24] = [3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24]
	local427
	isEGA
	[sizeRect 4]
)
(instance findSize of Code
	(properties)
	
	(method (doit param1)
		(TextSize @sizeRect param1 2207 315)
		(return (- (/ (- 180 (- [sizeRect 2] [sizeRect 0])) 2) 10))
	)
)

(instance startOfCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (Graph GDetect) 256)
					(= isEGA FALSE)
				else
					(= isEGA TRUE)
				)
				(theGlobalSound2 loop: 1 number: 111 play:)
				(kq init:)
				(= cycles 2)
			)
			(1
				(if isEGA
					(= seconds 2)
				else
					(fadeThePic doit: 0 self)
				)
			)
			(2 (= seconds 2))
			(3
				(if isEGA
					(= seconds 2)
				else
					(fadeThePic doit: 1 self)
				)
			)
			(4
				(kq dispose:)
				(self setScript: openingCredits self)
			)
			(5
				(if (== (theGlobalSound2 prevSignal?) -1)
					(self cue:)
				else
					(theGlobalSound2 client: self)
				)
			)
			(6 (= cycles 4))
			(7
				(self dispose:)
				(DisposeScript 107)
			)
		)
	)
)

(instance openingCredits of Script
	(properties)
	
	(method (changeState newState &tmp temp0 theY)
		(switch (= state newState)
			(0
				(Display 107 0 p_save saveBits1)
				(Display 107 0 p_save saveBits2)
				(if (== [local403 local402] 0)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(1
				(++ local427)
				(Message MsgGet 94 0 0 0 [local403 local402] @str1)
				(= theY (findSize doit: @str1))
				(++ local402)
				(= saveBits1
					(Display @str1
						p_color 7
						p_at 60 theY
						p_width 200
						p_font 3110
						p_mode teJustCenter
						p_save
					)
				)
				(Message MsgGet 94 0 0 0 [local403 local402] @str2)
				(++ local402)
				(= saveBits2
					(Display @str2
						p_color 7
						p_at 60 (+ theY 15)
						p_width 200
						p_font 2207
						p_mode teJustCenter
						p_save
					)
				)
				(= cycles 1)
			)
			(2
				(if isEGA
					(= seconds 2)
				else
					(fadeThePic doit: 0 self)
				)
			)
			(3 (= seconds 4))
			(4
				(if isEGA
					(= seconds 2)
				else
					(fadeThePic doit: 1 self)
				)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance kq of Actor
	(properties
		x 156
		y 89
		view 1092
		priority 14
		signal fixPriOn
	)
)

(instance fadeThePic of Code
	(properties)
	
	(method (doit param1 param2 &tmp temp0 temp1 temp2)
		(if (== param1 1)
			(= temp0 100)
			(while (> temp0 0)
				(Palette PALIntensity 0 231 temp0)
				(= temp1 0)
				(while (< temp1 10)
					(++ temp1)
				)
				(-- temp0)
			)
		else
			(= temp0 0)
			(while (< temp0 100)
				(Palette PALIntensity 0 231 temp0)
				(= temp1 0)
				(while (< temp1 10)
					(++ temp1)
				)
				(++ temp0)
			)
		)
		(= temp2 param2)
		(= param2 0)
		(temp2 cue:)
	)
)
