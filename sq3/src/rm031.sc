;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm031 0
)

(local
	local0
	local1
	[local2 2]
)
(instance rm031 of Room
	(properties)
	
	(method (init &tmp [temp0 50])
		(self picture: 31)
		(= showStyle HSHUTTER)
		(HandsOff)
		(= inCartoon TRUE)
		(TheMenuBar hide:)
		(StatusLine disable:)
		(Load VIEW 54)
		(if (not sawTerminator)
			(Load VIEW 58)
			(Load VIEW 60)
			(Load PICTURE 30)
			(Load SOUND 10)
			(Load SOUND 80)
		)
		(Load SOUND 9)
		(Load SOUND 79)
		(super init:)
		(ship init:)
		(self setScript: shipScript)
	)
)

(instance shipScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ship setMotion: MoveTo 164 110 self)
				(theMusic number: 9 play:)
			)
			(2
				(ship
					cycleSpeed: (if (!= global209 6) 2 else 0)
					setCycle: EndLoop self
				)
			)
			(3
				(if (== global209 6)
					(ship cel: 0 setLoop: 1 setCycle: EndLoop self)
					(hit number: 79 play:)
				else
					(= cycles 2)
				)
				(theMusic stop:)
			)
			(4 (= seconds 2) (hit stop:))
			(5
				(if (not sawTerminator)
					(theMusic number: 10 play:)
					(= seconds 3)
				else
					(theMusic fade:)
					(curRoom newRoom: 14)
				)
			)
			(6
				(termShip setLoop: 0 init:)
				(curRoom setScript: termScript)
			)
		)
	)
)

(instance termScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not sawTerminator)
					(termShip setCycle: EndLoop self)
				else
					(self changeState: 4)
				)
			)
			(1
				(if (< (termShip loop?) 6)
					(termShip cel: 0 setLoop: (+ (termShip loop?) 1))
					(self changeState: (- state 1))
				else
					(= sawTerminator 1)
					(termShip setCycle: Forward)
					(= seconds 3)
				)
			)
			(2
				(curRoom setScript: rm30Script)
			)
			(3
				(termShip setCycle: 0 setMotion: MoveTo 158 142 self)
			)
			(4 (= cycles 3))
			(5
				(termShip
					setMotion: MoveTo (termShip x?) (- (termShip y?) 20) self
				)
			)
			(6
				(termShip cel: 0 setLoop: 7 setCycle: EndLoop self)
			)
			(7
				(termShip cel: 0 setLoop: 8 setCycle: EndLoop self)
				(hit number: 80 play:)
				(theMusic stop:)
			)
			(8 (= seconds 2))
			(9 (curRoom newRoom: 14))
		)
	)
)

(instance rm30Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ship dispose:)
				(termShip dispose:)
				(curRoom drawPic: 30)
				(mouth init:)
				(starBar init: stopUpd:)
				(= cycles 2)
			)
			(1 (= seconds 3))
			(2
				(= local0
					(Display
						{...DEMRIFNOC YTITNEDI}
						p_font 601
						p_width 115
						p_color vLGREEN
						p_mode teJustCenter
						p_at 116 35
						p_save
					)
				)
				(= seconds 3)
			)
			(3
				(= local1
					(Display
						{OCLIW REGOR\n:218UO ESAC}
						p_font 601
						p_width 115
						p_color vLGREEN
						p_at 116 45
						p_mode teJustCenter
						p_save
					)
				)
				(= seconds 4)
			)
			(4
				(Display 31 0 108 local0)
				(Display 31 0 108 local1)
				(= cycles 5)
			)
			(5
				(= local0
					(Display
						{ROF DETNAW OCLIW\nDUARF ENIHCAM GNIDNEV\n\n:FFITNIALP\n.OC YTLEVON DIOZAPPIG_}
						p_font 601
						p_width 115
						p_color 10
						p_mode teJustCenter
						p_at 116 35
						p_save
					)
				)
				(= seconds 5)
			)
			(6
				(Display 31 0 p_restore local0)
				(= cycles 5)
			)
			(7
				(= local0
					(Display
						{:TNEMEGDUJ}
						p_font 601
						p_width 115
						p_color vLGREEN
						p_at 122 38
						p_mode teJustCenter
						p_save
					)
				)
				(= seconds 2)
			)
			(8 (= cycles 10))
			(9
				(termText init: setCycle: EndLoop self)
			)
			(10
				(termText setLoop: 2 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(11
				(mouth setMotion: MoveTo 162 190 self)
			)
			(12 (= seconds 2))
			(13
				(mouth stopUpd:)
				(starBar
					setMotion: MoveTo (- (starBar x?) 40) (starBar y?) self
				)
			)
			(14
				(mouth dispose:)
				(starBar dispose:)
				(termText dispose:)
				(Display 31 0 p_restore local0)
				(curRoom drawPic: 31)
				(termShip setLoop: 6 setCel: 0 init:)
				(= cycles 2)
			)
			(15
				(curRoom setScript: termScript)
			)
		)
	)
)

(instance ship of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 54
			setLoop: 0
			setCel: 0
			posn: 164 179
			setStep: 1 1
			ignoreActors: TRUE
			illegalBits: 0
			setCycle: 0
		)
	)
)

(instance termShip of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 60
			cel: 0
			posn: 158 152
			setStep: 1 1
			ignoreActors: TRUE
			illegalBits: 0
			setCycle: 0
		)
	)
)

(instance mouth of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 58
			setLoop: 0
			posn: 162 174
			setStep: 1 4
			ignoreActors: TRUE
			illegalBits: 0
			stopUpd:
		)
	)
)

(instance starBar of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 58
			setLoop: 3
			posn: 193 74
			setPri: 4
			setStep: 2 1
			ignoreActors: TRUE
			setCycle: 0
		)
	)
)

(instance termText of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 58
			setLoop: 1
			setCel: 0
			posn: 179 58
			setPri: 10
			ignoreActors: TRUE
		)
	)
)

(instance hit of Sound
	(properties
		priority 5
	)
)
