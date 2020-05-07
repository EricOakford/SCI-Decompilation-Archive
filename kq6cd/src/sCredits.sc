;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include sci.sh)
(use Main)
(use Timer)
(use System)

(public
	sCredits 0
)

(local
	local0 =  1
	local1
	local2
	local3
	local4
	local5
	local6
)
(instance sCredits of Script
	(properties)
	
	(method (init)
		(if (not register)
			(cast eachElementDo: #addToPic)
			(theIconBar disable:)
			(Cursor showCursor: 0)
			(Load rsFONT 2207 3110)
			(Load rsMESSAGE 194)
			(Load rsPIC 98 99)
			(songTimer setReal: self 240)
			(if (and (== msgType 2) (DoAudio 10 10))
				(DoAudio 10 2 2 0 236)
			else
				(theMusic number: 24 loop: 1 play: self)
			)
			(++ register)
		)
		(super init: &rest)
	)
	
	(method (changeState newState &tmp [temp0 50] [temp50 500] temp550 temp551)
		(switch (= state newState)
			(0
				(if local1
					(Message msgGET 194 3 0 0 local0 @temp0)
					(Message msgGET 194 3 0 0 (++ local0) @temp50)
				else
					(Message msgGET 194 0 0 0 local0 @temp0)
					(Message msgGET 194 0 0 0 (++ local0) @temp50)
				)
				(if (>= (++ local0) 32) (= local1 1) (= local0 1))
				(= local2
					(Display
						@temp0
						dsCOLOR
						0
						dsCOORD
						64
						61
						dsWIDTH
						200
						dsFONT
						3110
						dsALIGN
						1
						dsSAVEPIXELS
					)
				)
				(= local3
					(Display
						@temp50
						dsCOLOR
						0
						dsCOORD
						64
						81
						dsWIDTH
						200
						dsFONT
						2207
						dsALIGN
						1
						dsSAVEPIXELS
					)
				)
				(= local4
					(Display
						@temp0
						dsCOLOR
						7
						dsCOORD
						63
						60
						dsWIDTH
						200
						dsFONT
						3110
						dsALIGN
						1
						dsSAVEPIXELS
					)
				)
				(= local5
					(Display
						@temp50
						dsCOLOR
						7
						dsCOORD
						63
						80
						dsWIDTH
						200
						dsFONT
						2207
						dsALIGN
						1
						dsSAVEPIXELS
					)
				)
				(= seconds 6)
			)
			(1
				(Display 52 0 108 local5)
				(Display 52 0 108 local4)
				(Display 52 0 108 local3)
				(Display 52 0 108 local2)
				(if (and (not local1) (== local0 15))
					(= temp550 100)
					(while (> temp550 0)
						(Palette palSET_INTENSITY 0 255 temp550)
						(= temp551 0)
						(while (< temp551 10)
							(++ temp551)
						)
						(-- temp550)
					)
					(if global169 (DrawPic 98 15) else (DrawPic 98))
				)
				(= cycles 2)
			)
			(2
				(if (and (not local1) (== local0 15))
					(Palette palSET_INTENSITY 0 231 100)
				)
				(if (and local1 (== local0 1) local6)
					(= cycles 2)
				else
					(if local1 (= local6 1))
					(self init:)
				)
			)
			(3
				(if (== score possibleScore)
					(Format
						@temp50
						{You received %d out of %d points. You've completed %d percent of the main-path puzzles in King's Quest VI.}
						score
						possibleScore
						(/ (* score 100) possibleScore)
					)
				else
					(Format
						@temp50
						{You received %d out of %d points. You've completed approximately %d percent of the main-path puzzles in King's Quest VI.}
						score
						possibleScore
						(/ (* score 100) possibleScore)
					)
				)
				(= local2
					(Display
						@temp50
						dsCOLOR
						7
						dsCOORD
						64
						60
						dsWIDTH
						200
						dsFONT
						3110
						dsALIGN
						1
						dsSAVEPIXELS
					)
				)
				(= seconds 9)
			)
			(4
				(Display 52 0 108 local2)
				(= cycles 2)
			)
			(5
				(if (<= score 230)
					(Message msgGET 194 0 0 1 2 @temp50)
				else
					(Message msgGET 194 0 0 1 3 @temp50)
				)
				(Display
					@temp50
					dsCOLOR
					7
					dsCOORD
					64
					60
					dsWIDTH
					200
					dsFONT
					smallFont
					dsALIGN
					1
				)
				(= seconds 20)
			)
			(6
				(if global169 (DrawPic 99 15) else (DrawPic 99))
				(Cursor showCursor: 1)
			)
			(7
				(= temp550 100)
				(while (> temp550 0)
					(Palette palSET_INTENSITY 0 255 temp550)
					(= temp551 0)
					(while (< temp551 10)
						(++ temp551)
					)
					(-- temp550)
				)
				(= cycles 5)
			)
			(8 (= quit 1))
		)
	)
)

(instance songTimer of Timer
	(properties)
)
