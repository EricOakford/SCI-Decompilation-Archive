;;; Sierra Script 1.0 - (do not remove this comment)
;*************************************************************
;***
;*** TITLE 2
;***	 Demo version by Robert W. Lindsley
;***
;*************************************************************

(script#	TITLE2)
(include game.sh)
(use Main)
(use DoDisp)
(use InnRoom)
(use File)
(use System)

(public
	title2		0
)

(local
	retailer = FALSE		;is this a retailer
)

(instance title2 of InnRoom
	(properties
		picture			pTITLE
		prevRoom			BOARDS
	)
	(method (init)
		(super init:	&rest)
		(self setScript:	sTitle)
	)
)

(instance sTitle of Script
	(method (changeState ns &tmp [retailStr 300])
		(switchto (= state ns)
			(
				(globalSound setVol:	127)
				(= retailer (FileIO fileExists {retail}))
				(= seconds 2)
			)
			(
				(Display 230 0
					p_font 800
					p_at 95 10 
					p_color 223
				)
				(Display 230 0
					p_font 800
					p_at 93 7 
					p_color 165
				)

				(if retailer
					(= cycles 1)
				else
					(Display 230 1 
						p_font 800
						p_at 103 34 
						p_color 223
					)
					(Display 230 1 
						p_font 800
						p_at 101 30
						p_color 165
					)
					(= seconds 3)
				)
			)
			(
				(if retailer
					(retailFile 
						open: fRead, 
						read:	@retailStr 300
					)
					(DoDisplay
						setColor:	165 223,
						font:			800,
						posn:			10 125,
						addText:		@retailStr
					)
				else
					(DoDisplay
						setColor:	165 223,
						font:			800,
						posn:			10 120,
						addText:		{Call for a FREE* MEMBERSHIP KIT plus
										5 FREE Hours to try all the fun and
										games of ImagiNation!\n\n\n\n
										*$5.95 Shipping and Handling}
					)
				)
				(= seconds 15)
			)
			(
				(DoDisplay restore:)
				(theGame restart:)
			)
		)
	)
)


(instance retailFile of File
	(properties
		name			{retail.txt}
	)
)
