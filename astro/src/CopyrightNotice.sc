;;; Sierra Script 1.0 - (do not remove this comment)
(script# 890)
(include game.sh)
(use Main)
(use Game)
(use User)
(use Menu)
(use System)

(public
	rm890 0
)

(local
	saveBits
	saveBits2
)
(instance rm890 of Room
	(properties
		picture 150
	)
	
	(method (init &tmp evt [temp1 50])
		(= showStyle (| IRISOUT BLACKOUT))
		(User canInput: FALSE canControl: FALSE)
		(super init:)
		(TheMenuBar state: FALSE)
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
		(self setScript: rmScript)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (event type?)
			(= normalCursor ARROW_CURSOR)
			(theGame setCursor: normalCursor (HaveMouse))
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Display 890 0 p_restore saveBits)
				(Display 890 0 p_restore saveBits2)
				(= saveBits2
					(Display
						{Copyright notice\n\n}
						p_width 250
						p_at 30 25
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= saveBits
					(Display
						{\nThis free software is copyrighted. All\nrights are reserved. This software may\nnot be sold for profit. No part of this\nsoftware may be copied, reproduced,\ntranslated or reduced to any electronic\nmedium or machine readable form\nwithout prior written consent of Sierra\nOn-Line, Inc. P.O. Box 485, Coarsegold,\nCA 93614}
						p_width 250
						p_at 40 25
						p_mode teJustLeft
						p_font 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 12)
			)
			(1 (curRoom newRoom: 900))
		)
	)
)
