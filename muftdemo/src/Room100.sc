;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use TScripts)
(use Intrface)
(use GControl)
(use LoadMany)
(use DCIcon)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room100 0
)

(local
	local0 = [0 {TALES}]
	cycleLogo =  TRUE
	saveWndBack
)
(instance Room100 of Room
	(properties
		picture 106
		style FADEOUT
	)
	
	(method (init)
		(SetPort 0 0 200 320 0 0)
		(super init: &rest)
		(theGame setScript: theZapCursor)
		(LoadMany PICTURE 100 101 102)
		(LoadMany VIEW 100 108 618 849)
		(LoadMany SOUND 2 156)
		(sparkle1 init:)
		(sparkle2 init:)
		(curRoom setScript: startGame)
	)
	
	(method (doit)
		(super doit:)
		(if cycleLogo
			(Palette PALCycle 96 218 -1)
		)
	)
	
	(method (dispose)
		(startControls dispose:)
		(super dispose: &rest)
	)
)

(instance sparkle1 of Prop
	(properties
		x 133
		y 35
		view 108
	)
)

(instance sparkle2 of Prop
	(properties
		x 60
		y 155
		view 108
		loop 1
	)
)

(instance startGame of HandsOffScript
	
	(method (changeState newState &tmp [temp0 422])
		(switch (= state newState)
			(0
				(theMusic
					number: 48
					setVol: 127
					flags: mNOPAUSE
					setLoop: 1
					play:
				)
				(= cycles 2)
			)
			(1
				(theGame setCursor: 69 TRUE 304 172)
				(= ticks 120)
			)
			(2
				(= saveWndBack (systemWindow back?))
				(systemWindow back: 47)
				(Print 100 0
					#dispose
					#color 32
					#at -1 1
				)
				(= cycles 1)
			)
			(3
				(if (OneOf (theMusic prevSignal?) 20 30 -1)
					(if modelessDialog
						(modelessDialog dispose:)
					)
					(systemWindow back: saveWndBack)
					(sparkle1 setCycle: EndLoop self)
				else
					(= cycles 1)
					(-- state)
				)
			)
			(4
				(if (OneOf (theMusic prevSignal?) 30 -1)
					(sparkle1 dispose:)
					(sparkle2 setCycle: EndLoop self)
				else
					(= cycles 1)
					(-- state)
				)
			)
			(5
				(if
					(and
						(== (sparkle2 cel?) (sparkle2 lastCel:))
						(== (theMusic prevSignal?) -1)
					)
					(sparkle2 dispose:)
					(= cycles 1)
				else
					(= cycles 1)
					(-- state)
				)
			)
			(6 (= ticks 60))
			(7
				(= cycleLogo 0)
				(curRoom drawPic: 100 PIXELDISSOLVE)
				(theMusic number: 2 priority: 15 setLoop: -1 play:)
				(= ticks 120)
			)
			(8
				(sfx number: 156 play:)
				(magEffectLeft init: cycleSpeed: 24 setCycle: CycleTo 4 1 self)
				(magEffectRight init: cycleSpeed: 24 setCycle: CycleTo 4 1)
			)
			(9
				(curRoom overlay: 102 DISSOLVE)
				(magEffectLeft setPri: 15 setCycle: EndLoop self)
				(magEffectRight setPri: 15 setCycle: EndLoop)
			)
			(10
				(sfx stop:)
				(magEffectLeft dispose:)
				(magEffectRight dispose:)
				(= ticks 120)
			)
			(11
				(curRoom overlay: 101 DISSOLVE)
				(= ticks 180)
			)
			(12
				(Print 100 1
					#at 70 150
					#color 32
					#time 5
					#dispose
					self
				)
			)
			(13
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(SetPort 0 0 174 320 26 0)
				(LoadMany SOUND 122 154)
				(LoadMany VIEW 230 900)
				(LoadMany PICTURE 230)
				(curRoom newRoom: 230)
			)
		)
	)
)

(instance gameStart of SysWindow
	(properties
		top 50
		left 100
		bottom 110
		right 220
	)
)

(instance startControls of GameControls
	
	(method (init)
		(= window gameStart)
		(self add: iconRest iconNew)
		(super init: &rest)
	)
)

(instance iconNew of CodeIcon
	(properties
		view 849
		loop 1
		cel 0
		nsLeft 60
		nsTop 0
		cursor 80
		signal (| HIDEBAR FIXED_POSN RELVERIFY)
		highlightColor 5
		lowlightColor 64
	)
	
	(method (select)
		(if (super select: &rest)
			(curRoom newRoom: 1)
		)
	)
)

(instance iconRest of CodeIcon
	(properties
		view 849
		loop 0
		cel 0
		nsTop 0
		cursor 80
		highlightColor 5
		lowlightColor 64
	)
	
	(method (select)
		(if (super select: &rest)
			(theGame restore:)
		)
	)
)

(instance magEffectLeft of Prop
	(properties
		x 78
		y 154
		view 100
	)
)

(instance magEffectRight of Prop
	(properties
		x 222
		y 154
		view 100
		loop 1
	)
)

(instance sfx of Sound
	(properties
		flags mNOPAUSE
		number 156
	)
)

(instance godmother of DCIcon
	(properties
		view 618
	)
	
	(method (init)
		((= cycler (BegLoop new:)) init: self)
	)
)
