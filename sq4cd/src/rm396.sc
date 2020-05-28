;;; Sierra Script 1.0 - (do not remove this comment)
(script# 396)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Sound)
(use Actor)
(use System)

(public
	rm396 0
)

(local
	saveBits
	local1
	[str 200]
	[str2 200]
)
(procedure (ATMDisplay)
	(if argc
		(= saveBits
			(Display
				&rest
				100 82 60	;p_at
				p_mode teJustLeft
				p_font 68
				p_color myTextColor7
				p_width 154
				p_save
			)
		)
	else
		(Display 396 0 p_restore saveBits)
	)
	(return saveBits)
)

(instance rm396 of SQRoom
	(properties
		picture 396
		style FADEOUT
	)
	
	(method (init)
		(but1 init:)
		(but2 init:)
		(but3 init:)
		(buckScreen init:)
		(ego init: hide:)
		(super init:)
		(music setVol: 25)
		(self setRegions: MALL)
		(curRoom setScript: startUpScript)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
	)
)

(instance startUpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable: ICON_CONTROL)
				(= seconds 3)
			)
			(1
				(= ticks 30)
				(buckScreen hide:)
			)
			(2
				(Message MsgGet curRoomNum 3 0 8 1 @str2)
				(ATMDisplay @str2)
				(= local1 0)
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_LOOK ICON_TALK ICON_TASTE ICON_SMELL ICON_ITEM ICON_CONTROL)
				(self dispose:)
			)
		)
	)
)

(instance shutDownScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
				(buckScreen show:)
			)
			(1
				(ego view: 374 loop: 1)
				(theIconBar enable: ICON_CONTROL)
				(curRoom newRoom: 395)
				(self dispose:)
			)
		)
	)
)

(instance but1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFX number: 102 loop: 0 play:)
				(client setCel: 1)
				(= ticks 30)
			)
			(1
				(client setCel: 0)
				(if (== local1 0)
					(= local1 1)
					(ATMDisplay)
					(Message MsgGet curRoomNum 3 0 5 1 @str)
					(ATMDisplay
						(Format @str2 @str buckazoidsInATM)
					)
				)
				(= cycles 10)
			)
			(2 (self dispose:))
		)
	)
)

(instance butPushScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFX number: 102 loop: 0 play:)
				(client setCel: 1)
				(= ticks 30)
			)
			(1
				(client setCel: 0)
				(= cycles 2)
			)
			(2 (self dispose:))
		)
	)
)

(instance but2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFX number: 102 loop: 0 play:)
				(client setCel: 1)
				(= ticks 30)
			)
			(1
				(client setCel: 0)
				(= cycles 2)
			)
			(2
				(ATMDisplay)
				(= cycles 2)
				(if (== local1 0)
					(= local1 2)
					(Message MsgGet curRoomNum 3 0 11 1 @str)
					(ATMDisplay @str)
				else
					(ego get: 0)
					(= local1 3)
					(= buckazoids (+ buckazoids buckazoidsInATM))
					(= buckazoidsInATM 0)
					(Message MsgGet curRoomNum 3 0 10 1 @str2)
					(ATMDisplay @str2)
				)
				(= cycles 2)
			)
			(3 (self dispose:))
		)
	)
)

(instance but3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFX number: 102 loop: 0 play:)
				(client setCel: 1)
				(= ticks 30)
			)
			(1
				(ATMDisplay)
				(client setCel: 0)
				(if (== local1 0)
					(curRoom setScript: shutDownScript)
				else
					(= local1 0)
					(Message MsgGet curRoomNum 3 0 8 1 @str2)
					(ATMDisplay @str2)
					(= cycles 2)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance buckScreen of View
	(properties
		x 106
		y 83
		view 394
	)
)

(instance but1 of Sq4Prop
	(properties
		x 20
		y 83
		view 810
	)
	
	(method (doVerb)
		(self setScript: but1Script)
	)
)

(instance but2 of Sq4Prop
	(properties
		x 20
		y 110
		view 810
	)
	
	(method (doVerb)
		(if (not (== local1 1))
			(self setScript: but2Script)
		else
			(self setScript: butPushScript)
		)
	)
)

(instance soundFX of Sound
	(properties
		number 102
		priority 5
	)
)

(instance but3 of Sq4Prop
	(properties
		x 20
		y 137
		view 810
	)
	
	(method (doVerb)
		(self setScript: but3Script)
	)
)
