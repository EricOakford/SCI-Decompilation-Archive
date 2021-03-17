;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SPEEDTST.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Steve Conrad
;;;;
;;;;	   SpeedTest returns a value from 0 to 10 which signifies the
;;;;	relative speed of a machine's graphics processing power.
;;;;	It must be run in a room that has a black background.
;;;;
;;;;	   The fastest value is 0 and the slowest is 10. The detailLevel
;;;;	can be set to whatever is appropriate. However,	even if the 
;;;;	speed of the interpreter/graphics changes, this will be updated
;;;;	appropriately to stay consistent.
;;;;
;;;;	The following list relates values to machines:
;;;;
;;;;			3 - 486 DX  66	(Local Bus)
;;;;			4 - 486 DX  66 
;;;;			5 - 486 DX  50
;;;;			6 - 486 DX  33
;;;;			7 - 486 SX  25
;;;;			7 - 486 SLC 50
;;;;			7 - 386 DX  40
;;;;			7 - 386 DX  33
;;;;			8 - 386 DX  25
;;;;			9 - 386 SX  16
;;;;
;;;;	Procedure:
;;;;		SpeedTest

(script# SPEEDTEST_ROOM)
(include game.sh)
(use Actor)

;;;(procedure 
;;;	SpeedTest
;;;)

(public 
	SpeedTest 0
) 

(procedure (SpeedTest &tmp endTime distance speedRating)
	
	(fred
		init:	,
		posn:	0 100
	)

	(= endTime (+ (GetTime) 60))

	(while (< (GetTime) endTime)
		(fred doit:)
		(UpdateScreenItem	fred)
		(FrameOut)
	)

	(= distance (fred x?))

	(fred dispose:)

	(if (>= distance 100)
		(= speedRating 0)
	else
		(= speedRating (- 10 (/ distance 10)))
	)

	(DisposeScript SPEEDTEST_ROOM speedRating)
)

(instance fred of Actor
	(properties
		view			64980
		loop			0
		cel			0
	)

	(method (doit)
		(++ x)
	)
)

