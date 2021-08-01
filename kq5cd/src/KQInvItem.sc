;;; Sierra Script 1.0 - (do not remove this comment)
(script# 758)
(include sci.sh)
(use Main)
(use KQ5InvWindow)
(use KQCursor)
(use IconBar)
(use Invent)
(use User)
(use System)

(public
	KQInv 0
)

(class KQInvItem of InvI
	(properties
		view 0
		loop 0
		cel 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		state $0000
		cursor 999
		type $4000
		message 4
		modifiers $0000
		signal $0000
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		description 0
		owner 0
		script 0
		value 0
	)
	
	(method (highlight param1 &tmp theNsTop theNsLeft theNsBottom theNsRight temp4)
		(= temp4
			(if (and argc param1) highlightColor else lowlightColor)
		)
		(= theNsTop nsTop)
		(= theNsLeft nsLeft)
		(= theNsBottom nsBottom)
		(= theNsRight nsRight)
		(Graph
			grDRAW_LINE
			theNsTop
			theNsLeft
			theNsTop
			theNsRight
			temp4
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			theNsTop
			theNsRight
			theNsBottom
			theNsRight
			temp4
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			theNsBottom
			theNsRight
			theNsBottom
			theNsLeft
			temp4
			-1
			-1
		)
		(Graph
			grDRAW_LINE
			theNsBottom
			theNsLeft
			theNsTop
			theNsLeft
			temp4
			-1
			-1
		)
		(Graph
			grUPDATE_BOX
			(- nsTop 2)
			(- nsLeft 2)
			(+ nsBottom 2)
			(+ nsRight 2)
			1
		)
	)
	
	(method (doVerb theVerb &tmp temp0 newEvent temp2)
		(if (== theVerb 2)
			(SpeakAudio description)
		else
			(if (User canInput:)
				(= temp0 (theGame setCursor: theXCursor))
				(= global126 0)
				(= temp2 (GetTime))
				(while (< (Abs (- temp2 (GetTime))) 40)
					(breakif (OneOf ((= newEvent (Event new:)) type?) 4 1)
					)
					(newEvent dispose:)
				)
				(if (IsObject newEvent) (newEvent dispose:))
				(theGame setCursor: temp0 1)
			)
			(super doVerb: theVerb &rest)
		)
	)
)

(instance KQInv of Inv
	(properties
		normalHeading {Graham is carrying:}
		empty {Nothing!}
		curScore 9411
	)
	
	(method (init)
		((= inventory self)
			add:
				Ok
				(Key cursor: keyCursor yourself:)
				(Pie cursor: pieCursor yourself:)
				(Golden_Needle cursor: needleCursor yourself:)
				(Coin cursor: silverCursor yourself:)
				(Fish cursor: fishCursor yourself:)
				(Brass_Bottle cursor: bottleCursor yourself:)
				(Staff cursor: staffCursor yourself:)
				(Shoe cursor: shoeCursor yourself:)
				(Heart cursor: heartCursor yourself:)
				(Harp cursor: harpCursor yourself:)
				(Gold_Coin cursor: goldCursor yourself:)
				(Marionette cursor: puppetCursor yourself:)
				(Pouch cursor: pouchCursor yourself:)
				(Emeralds cursor: threeEmeraldCursor yourself:)
				(Spinning_Wheel cursor: wheelCursor yourself:)
				(Stick cursor: stickCursor yourself:)
				(Honeycomb cursor: honeyCursor yourself:)
				(Beeswax cursor: waxCursor yourself:)
				(Leg_of_Lamb cursor: lambCursor yourself:)
				(Rope cursor: ropeCursor yourself:)
				(Crystal cursor: crystalCursor yourself:)
				(Hammer cursor: hammerCursor yourself:)
				(Shell cursor: shellCursor yourself:)
				(Bag_of_Peas cursor: peasCursor yourself:)
				(Locket cursor: locketCursor yourself:)
				(Cloak cursor: cloakCursor yourself:)
				(Amulet cursor: amuletCursor yourself:)
				(Wand
					cursor: (if (not (Btst 60)) cwandCursor else cGlowWandCursor)
					yourself:
				)
				(Sled cursor: sledCursor yourself:)
				(Iron_Bar cursor: ironbarCursor yourself:)
				(Fishhook cursor: hookCursor yourself:)
				(Moldy_Cheese cursor: cheeseCursor yourself:)
				(Elf_Shoes_ cursor: elfShoeCursor yourself:)
				(Tambourine cursor: tambourineCursor yourself:)
				(Mordack_s_Wand cursor: mWandCursor yourself:)
				(Hairpin cursor: hairpinCursor yourself:)
				(Cat_Fish cursor: mFishCursor yourself:)
				(Mongoose_Spell cursor: cGlowWandCursor yourself:)
				(Bunny_Spell cursor: cGlowWandCursor yourself:)
				(Rain_Spell cursor: cGlowWandCursor yourself:)
				(Tiger_Spell cursor: cGlowWandCursor yourself:)
				(invLook cursor: eyeCursor yourself:)
				(invHand cursor: handCursor yourself:)
				(invSelect cursor: normalCursor yourself:)
				(invHelp cursor: helpCursor yourself:)
				(ok cursor: arrowCursor yourself:)
			eachElementDo: #highlightColor 0
			eachElementDo:
				#lowlightColor
				(switch numColors
					(256 23)
					(32 15)
					(else  7)
				)
			eachElementDo: #init
			window: KQ5InvWindow
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
	)
)

(instance Ok of KQInvItem
	(properties
		view 895
	)
)

(instance Key of KQInvItem
	(properties
		view 892
		loop 2
		cel 4
		signal $0002
		description 84
		owner 23
	)
)

(instance Pie of KQInvItem
	(properties
		view 892
		cel 9
		signal $0002
		description 85
		owner 206
	)
)

(instance Golden_Needle of KQInvItem
	(properties
		view 892
		cel 10
		signal $0002
		description 86
		owner 27
		name "Golden Needle"
	)
)

(instance Coin of KQInvItem
	(properties
		view 892
		cel 1
		signal $0002
		description 87
		owner 4
	)
)

(instance Fish of KQInvItem
	(properties
		view 891
		signal $0002
		description 88
		owner 4
	)
)

(instance Brass_Bottle of KQInvItem
	(properties
		view 891
		cel 1
		signal $0002
		description 89
		owner 18
		name "Brass Bottle"
	)
	
	(method (doVerb theVerb &tmp [temp0 75])
		(return
			(switch theVerb
				(3
					(inventory hide:)
					(curRoom newRoom: 208)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance Staff of KQInvItem
	(properties
		view 891
		cel 2
		signal $0002
		description 90
		owner 17
	)
)

(instance Shoe of KQInvItem
	(properties
		view 892
		cel 6
		signal $0002
		description 91
		owner 15
	)
)

(instance Heart of KQInvItem
	(properties
		view 892
		loop 2
		cel 7
		signal $0002
		description 92
		owner 21
	)
)

(instance Harp of KQInvItem
	(properties
		view 893
		signal $0002
		description 93
		owner 9
	)
)

(instance Gold_Coin of KQInvItem
	(properties
		view 891
		cel 3
		signal $0002
		description 94
		owner 18
		name "Gold Coin"
	)
)

(instance Marionette of KQInvItem
	(properties
		view 892
		cel 7
		signal $0002
		description 95
	)
)

(instance Pouch of KQInvItem
	(properties
		view 892
		loop 2
		cel 5
		signal $0002
		owner 23
	)
	
	(method (doVerb theVerb &tmp [temp0 75])
		(return
			(switch theVerb
				(2
					(cond 
						((== numEmeralds -1) (SpeakAudio 96))
						(numEmeralds (SpeakAudio
							(switch numEmeralds
								(3 97)
								(2 98)
								(1 99)
							)))
						(else (SpeakAudio 100))
					)
				)
				(3
					(if (== numEmeralds -1)
						(= numEmeralds 3)
						(proc0_30 131 891 0 6)
						(inventory hide:)
						(self loop: 4 cel: 1)
						(ego get: 14)
						(inventory show: ego)
					else
						(SpeakAudio 132)
					)
					(return 0)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance Emeralds of KQInvItem
	(properties
		view 891
		cel 6
		signal $0002
	)
	
	(method (doVerb theVerb &tmp [temp0 50])
		(switch theVerb
			(2
				(SpeakAudio
					(switch numEmeralds
						(3 101)
						(2 102)
						(1 103)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Spinning_Wheel of KQInvItem
	(properties
		view 892
		loop 2
		cel 6
		description 104
		owner 23
		name "Spinning Wheel"
	)
)

(instance Stick of KQInvItem
	(properties
		view 892
		loop 2
		cel 1
		signal $0002
		description 105
		owner 11
	)
)

(instance Honeycomb of KQInvItem
	(properties
		view 891
		cel 7
		signal $0002
		description 106
		owner 11
	)
)

(instance Beeswax of KQInvItem
	(properties
		view 892
		loop 2
		signal $0002
		description 107
	)
)

(instance Leg_of_Lamb of KQInvItem
	(properties
		view 892
		cel 4
		signal $0002
		owner 28
		name "Leg of Lamb"
	)
	
	(method (doVerb theVerb &tmp [temp0 50])
		(switch theVerb
			(2
				(if eatLambCount (SpeakAudio 109) else (SpeakAudio 108))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Rope of KQInvItem
	(properties
		view 892
		cel 8
		signal $0002
		description 110
		owner 86
	)
)

(instance Crystal of KQInvItem
	(properties
		view 893
		cel 1
		signal $0002
		description 111
		owner 38
	)
)

(instance Hammer of KQInvItem
	(properties
		view 892
		loop 4
		signal $0002
		description 112
	)
)

(instance Shell of KQInvItem
	(properties
		view 893
		cel 2
		signal $0002
		description 113
	)
)

(instance Bag_of_Peas of KQInvItem
	(properties
		view 893
		cel 3
		signal $0002
		description 114
		owner 56
		name "Bag of Peas"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 63)
					(SpeakAudio 115)
				else
					(SpeakAudio description)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Locket of KQInvItem
	(properties
		view 893
		cel 6
		signal $0002
		description 116
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3 (SpeakAudio 133) (return 0))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance Cloak of KQInvItem
	(properties
		view 892
		cel 2
		signal $0002
		description 117
		owner 203
	)
)

(instance Amulet of KQInvItem
	(properties
		view 892
		loop 2
		cel 2
		signal $0002
		description 118
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 84)
					(SpeakAudio 119)
				else
					(SpeakAudio description)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Wand of KQInvItem
	(properties
		view 892
		signal $0002
		description 120
	)
	
	(method (moveTo)
		(if (and (Btst 60) (!= cursor cGlowWandCursor))
			(= loop 4)
			(= cel 2)
			(= cursor cGlowWandCursor)
		)
		(super moveTo: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 60)
					(SpeakAudio 121)
				else
					(SpeakAudio description)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Sled of KQInvItem
	(properties
		view 892
		cel 3
		signal $0002
		description 122
		owner 204
	)
)

(instance Iron_Bar of KQInvItem
	(properties
		view 893
		cel 5
		signal $0002
		description 123
		owner 44
		name "Iron Bar"
	)
)

(instance Fishhook of KQInvItem
	(properties
		view 893
		cel 11
		signal $0002
		description 124
		owner 54
	)
)

(instance Moldy_Cheese of KQInvItem
	(properties
		view 893
		cel 7
		signal $0002
		description 692
		owner 67
		name "Moldy Cheese"
	)
)

(instance Elf_Shoes_ of KQInvItem
	(properties
		view 892
		loop 2
		cel 8
		signal $0002
		description 125
		owner 83
		name "Elf Shoes_"
	)
)

(instance Tambourine of KQInvItem
	(properties
		view 892
		loop 2
		cel 3
		signal $0002
		description 126
		owner 13
	)
)

(instance Mordack_s_Wand of KQInvItem
	(properties
		view 893
		cel 8
		signal $0002
		description 127
		owner 13
		name "Mordack's Wand"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (Btst 60)
					(SpeakAudio 128)
				else
					(SpeakAudio description)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Hairpin of KQInvItem
	(properties
		view 893
		cel 9
		signal $0002
		description 129
		owner 55
	)
)

(instance Cat_Fish of KQInvItem
	(properties
		view 893
		cel 10
		signal $0002
		description 88
		owner 51
		name "Cat Fish"
	)
)

(instance Mongoose_Spell of KQInvItem
	(properties
		view 712
		loop 2
		signal $0002
		owner 65
		name "Mongoose Spell"
	)
)

(instance Bunny_Spell of KQInvItem
	(properties
		view 712
		loop 2
		cel 1
		signal $0002
		owner 65
		name "Bunny Spell"
	)
)

(instance Rain_Spell of KQInvItem
	(properties
		view 712
		loop 2
		cel 2
		signal $0002
		owner 65
		name "Rain Spell"
	)
)

(instance Tiger_Spell of KQInvItem
	(properties
		view 712
		loop 2
		cel 3
		signal $0002
		owner 65
		name "Tiger Spell"
	)
)

(instance ok of IconI
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor 999
		signal $0043
		helpStr 9250
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (if isVGA 19 else 7)
		)
		(super init:)
	)
)

(instance invLook of IconI
	(properties
		view 901
		loop 2
		cel 0
		cursor 7
		message 2
		helpStr 9251
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (if isVGA 19 else 7)
		)
		(super init:)
	)
)

(instance invHand of IconI
	(properties
		view 901
		loop 0
		cel 0
		cursor 8
		message 3
		helpStr 9252
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (if isVGA 19 else 7)
		)
		(super init:)
	)
)

(instance invHelp of IconI
	(properties
		view 901
		loop 1
		cel 0
		cursor 70
		message 6
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (if isVGA 19 else 7)
		)
		(super init:)
	)
)

(instance invSelect of IconI
	(properties
		view 901
		loop 4
		cel 0
		cursor 999
		helpStr 9253
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (if isVGA 19 else 7)
		)
		(super init:)
	)
)

(instance keyCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 7
		x 20
		y 6
	)
	
	(method (init)
		(if global400 (self number: 15 yourself:))
		(super init: &rest)
	)
)

(instance pieCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 13
		x 10
		y 5
	)
	
	(method (init)
		(if global400 (self number: 16 yourself:))
		(super init: &rest)
	)
)

(instance needleCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 14
		x 1
		y 1
	)
	
	(method (init)
		(if global400 (self number: 17 yourself:))
		(super init: &rest)
	)
)

(instance silverCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 3
		x 9
		y 6
	)
	
	(method (init)
		(if global400 (self number: 18 yourself:))
		(super init: &rest)
	)
)

(instance fishCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 12
		x 10
		y 6
	)
	
	(method (init)
		(if global400 (self number: 19 yourself:))
		(super init: &rest)
	)
)

(instance bottleCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 6
		x 4
		y 11
	)
	
	(method (init)
		(if global400 (self number: 500 yourself:))
		(super init: &rest)
	)
)

(instance staffCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 4
		x 1
		y 1
	)
	
	(method (init)
		(if global400 (self number: 21 yourself:))
		(super init: &rest)
	)
)

(instance heartCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 10
		x 10
		y 9
	)
	
	(method (init)
		(if global400 (self number: 22 yourself:))
		(super init: &rest)
	)
)

(instance harpCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 9
		x 7
		y 8
	)
	
	(method (init)
		(if global400 (self number: 23 yourself:))
		(super init: &rest)
	)
)

(instance goldCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 5
		x 9
		y 6
	)
	
	(method (init)
		(if global400 (self number: 18 yourself:))
		(super init: &rest)
	)
)

(instance puppetCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 10
		x 5
		y 11
	)
	
	(method (init)
		(if global400 (self number: 25 yourself:))
		(super init: &rest)
	)
)

(instance pouchCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 8
		x 10
		y 6
	)
	
	(method (init)
		(if global400 (self number: 26 yourself:))
		(super init: &rest)
	)
)

(instance oneEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 7
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance twoEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 8
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance threeEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 9
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance wheelCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 9
		x 10
		y 10
	)
	
	(method (init)
		(if global400 (self number: 28 yourself:))
		(super init: &rest)
	)
)

(instance stickCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 1
		x 10
		y 8
	)
	
	(method (init)
		(if global400 (self number: 29 yourself:))
		(super init: &rest)
	)
)

(instance honeyCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 15
		x 10
		y 6
	)
	
	(method (init)
		(if global400 (self number: 30 yourself:))
		(super init: &rest)
	)
)

(instance waxCursor of KQCursor
	(properties
		view 941
		loop 3
		x 10
		y 7
	)
	
	(method (init)
		(if global400 (self number: 31 yourself:))
		(super init: &rest)
	)
)

(instance lambCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 6
		x 7
		y 5
	)
	
	(method (init)
		(if global400 (self number: 32 yourself:))
		(super init: &rest)
	)
)

(instance ropeCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 11
		x 11
		y 9
	)
	
	(method (init)
		(if global400 (self number: 33 yourself:))
		(super init: &rest)
	)
)

(instance crystalCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 12
		x 2
		y 8
	)
	
	(method (init)
		(if global400 (self number: 34 yourself:))
		(super init: &rest)
	)
)

(instance hammerCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 6
		x 4
		y 8
	)
	
	(method (init)
		(if global400 (self number: 35 yourself:))
		(super init: &rest)
	)
)

(instance shellCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 15
		x 7
		y 12
	)
	
	(method (init)
		(if global400 (self number: 36 yourself:))
		(super init: &rest)
	)
)

(instance peasCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 2
		x 7
		y 12
	)
	
	(method (init)
		(if global400 (self number: 37 yourself:))
		(super init: &rest)
	)
)

(instance locketCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 13
		x 14
		y 11
	)
	
	(method (init)
		(if global400 (self number: 38 yourself:))
		(super init: &rest)
	)
)

(instance cloakCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 4
		x 10
		y 9
	)
	
	(method (init)
		(if global400 (self number: 39 yourself:))
		(super init: &rest)
	)
)

(instance amuletCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 2
		x 4
		y 10
	)
	
	(method (init)
		(if global400 (self number: 40 yourself:))
		(super init: &rest)
	)
)

(instance cwandCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 2
		x 13
		y 2
	)
	
	(method (init)
		(if global400 (self number: 41 yourself:))
		(super init: &rest)
	)
)

(instance sledCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 5
		x 12
		y 5
	)
	
	(method (init)
		(if global400 (self number: 42 yourself:))
		(super init: &rest)
	)
)

(instance ironbarCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 14
		x 13
		y 13
	)
	
	(method (init)
		(if global400 (self number: 43 yourself:))
		(super init: &rest)
	)
)

(instance hookCursor of KQCursor
	(properties
		view 941
		loop 5
		x 5
		y 10
	)
	
	(method (init)
		(if global400 (self number: 44 yourself:))
		(super init: &rest)
	)
)

(instance cheeseCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 4
		x 10
		y 7
	)
	
	(method (init)
		(if global400 (self number: 45 yourself:))
		(super init: &rest)
	)
)

(instance elfShoeCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 11
		x 10
		y 10
	)
	
	(method (init)
		(if global400 (self number: 46 yourself:))
		(super init: &rest)
	)
)

(instance tambourineCursor of KQCursor
	(properties
		view 941
		loop 3
		cel 3
		x 13
		y 7
	)
	
	(method (init)
		(if global400 (self number: 47 yourself:))
		(super init: &rest)
	)
)

(instance mWandCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 5
		x 14
		y 6
	)
	
	(method (init)
		(if global400 (self number: 48 yourself:))
		(super init: &rest)
	)
)

(instance hairpinCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 1
		x 7
		y 11
	)
	
	(method (init)
		(if global400 (self number: 49 yourself:))
		(super init: &rest)
	)
)

(instance mFishCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 10
		x 13
		y 6
	)
	
	(method (init)
		(if global400 (self number: 19 yourself:))
		(super init: &rest)
	)
)

(instance shoeCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 8
		x 11
		y 7
	)
	
	(method (init)
		(if global400 (self number: 51 yourself:))
		(super init: &rest)
	)
)

(instance halfLambCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 7
		x 7
		y 5
	)
	
	(method (init)
		(if global400 (self number: 32 yourself:))
		(super init: &rest)
	)
)

(instance cGlowWandCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 12
		x 7
		y 6
	)
	
	(method (init)
		(if global400 (self number: 41 yourself:))
		(super init: &rest)
	)
)

(instance emptyBagCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 3
		x 9
		y 10
	)
	
	(method (init)
		(if global400 (self number: 54 yourself:))
		(super init: &rest)
	)
)

(instance arrowCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 7
	)
	
	(method (init)
		(if global400 (self number: 999 yourself:))
		(super init: &rest)
	)
)

(instance handCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 2
		x 14
		y 10
	)
	
	(method (init)
		(if global400 (self number: 8 yourself:))
		(super init: &rest)
	)
)

(instance helpCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 4
		x 10
		y 15
	)
	
	(method (init)
		(if global400 (self number: 70 yourself:))
		(super init: &rest)
	)
)

(instance eyeCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 1
		x 12
		y 12
	)
	
	(method (init)
		(if global400 (self number: 7 yourself:))
		(super init: &rest)
	)
)

(instance theXCursor of KQCursor
	(properties
		view 942
		cel 10
		x 6
		y 6
	)
	
	(method (init)
		(if global400 (self number: 5 yourself:))
		(super init: &rest)
	)
)
