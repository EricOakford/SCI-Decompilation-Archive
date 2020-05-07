;;; Sierra Script 1.0 - (do not remove this comment)
(script# 905)
(include sci.sh)
(use Main)
(use Print)
(use LoadMany)
(use Sound)
(use System)

(public
	aboutCode 0
)

(local
	local0
	local1
	local2
)
(instance aboutCode of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 20])
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(if (== msgType 2) (= local2 1) (= msgType 1))
				(Format @temp1 905 0)
				(if (FileIO fiEXISTS @temp1) (= local0 1))
				(switch
					(= temp0
						(if local0
							(Print
								posn: 75 60
								font: 4
								addButton: 1 0 0 1 0 24 0 908
								addButton: 2 0 0 2 0 24 18 908
								addButton: 3 0 0 4 0 0 36 908
								addButton: 4 0 0 3 0 32 54 908
								addButton: 5 0 0 19 0 0 72 908
								init:
							)
						else
							(Print
								posn: 75 75
								font: 4
								addButton: 1 0 0 1 0 24 0 908
								addButton: 2 0 0 2 0 24 18 908
								addButton: 3 0 0 4 0 0 36 908
								addButton: 4 0 0 3 0 32 54 908
								init:
							)
						)
					)
					(1
						(curRoom setScript: oneThroughFive)
					)
					(2
						(curRoom setScript: sixScript)
					)
					(3 (curRoom setScript: tips))
					(4
						(curRoom setScript: walkThrough)
					)
					(5
						(curRoom setScript: damnedAd)
					)
					(else  (self dispose:))
				)
			)
		)
	)
)

(instance oneThroughFive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 5 0 self 908)
			)
			(1
				(messager say: 0 0 11 0 self 908)
			)
			(2
				(messager say: 0 0 12 0 self 908)
			)
			(3
				(messager say: 0 0 13 0 self 908)
			)
			(4
				(messager say: 0 0 14 0 self 908)
			)
			(5
				(self dispose:)
				(if local2 (= msgType 2))
				(LoadMany 0 905)
			)
		)
	)
)

(instance sixScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Printf
					{King's Quest VI\nVersion: 1.000.00G\nThis entire work is Copyright 1992-93\nSierra On-Line, Inc. All rights reserved.}
				)
				(= cycles 1)
			)
			(1
				(messager say: 0 0 15 0 self 908)
			)
			(2
				(self dispose:)
				(if local2 (= msgType 2))
				(LoadMany 0 905)
			)
		)
	)
)

(instance tips of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 16 0 self 908)
			)
			(1
				(self dispose:)
				(if local2 (= msgType 2))
				(LoadMany 0 905)
			)
		)
	)
)

(instance walkThrough of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 18 0 self 908)
			)
			(1
				(self dispose:)
				(if local2 (= msgType 2))
				(LoadMany 0 905)
			)
		)
	)
)

(instance damnedAd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (theGame isHandsOn?) (= local1 1))
				(theGame handsOff: killSound: 1)
				(if (DoSound sndGET_AUDIO_CAPABILITY)
					(= cycles 1)
				else
					(messager say: 0 0 21 1 self 908)
				)
			)
			(1
				(if (DoSound sndGET_AUDIO_CAPABILITY)
					(localMusic number: 11 init: play: self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (DoSound sndGET_AUDIO_CAPABILITY)
					(messager say: 0 0 20 1 self 908)
				else
					(= cycles 1)
				)
			)
			(3
				(if local1 (theGame handsOn: killSound: 0))
				(localMusic dispose:)
				(self dispose:)
				(if local2 (= msgType 2))
				(LoadMany 0 905)
			)
		)
	)
)

(instance localMusic of Sound
	(properties)
)
