;;; Sierra Script 1.0 - (do not remove this comment)

; Event Flags
; flags are assigned for every room after ego leaves it.
; if these aren't set, then ego has never been in the room before.
(enum
	fBeenIn10 			;0
	fBeenIn11 			;1
	fBeenIn12 			;2
	fBeenIn13 			;3
	fBeenIn14 			;4
	fBeenIn15			;5
	fBeenIn16			;6
	fBeenIn17			;7
	fBeenIn18 			;8
	fBeenIn19 			;9
	fBeenIn20			;10 ;unused (Room 20 does not exist)
	fBeenIn21			;11
	fBeenIn22			;12
	fBeenIn23			;13
	fBeenIn24			;14
	fBeenIn25			;15
	fBeenIn26			;16
	fBeenIn27			;17
	fBeenIn28 	;18
	fBeenIn29 	;19
	fBeenIn30 		;20
	fBeenIn31 		;21
	fBeenIn32 		;22 ;unused (Room 32 is the Wizard's Game)
	fBeenIn33 			;23
	fBeenIn34 			;24
	fBeenIn35 			;25
	fBeenIn36 			;26
	fBeenIn37 		;27
	fBeenIn38 	;28
	fBeenIn39 	;29 ;unused
	fBeenIn40 		;30 ;unused
	fBeenIn41 		;31
	fBeenIn42 			;32
	fBeenIn43 			;33
	fBeenIn44 			;34
	fBeenIn45 		;35
	fBeenIn46 			;36 ;unused (deleted Goblin Cave scene)
	fBeenIn47 			;37 ;unused (deleted Goblin Cave scene)
	fBeenIn48 			;38 ;unused (deleted Goblin Cave scene)
	fBeenIn49 			;39 ;unused (deleted Goblin Cave scene)
	fBeenIn50 			;40 ;unused (deleted Goblin Cave scene)
	fBeenIn51 			;41
	fBeenIn52 			;42
	fBeenIn53 				;43
	fBeenIn54 	;44
	fBeenIn55 	;45
	fBeenIn56 			;46
	fBeenIn57 			;47
	fBeenIn58 			;48
	fBeenIn59 			;49 ;unused (Room 59 does not exist)
	fBeenIn60 				;50
	fBeenIn61 			;51
	fBeenIn62 			;52
	fBeenIn63 			;53
	fBeenIn64 	;54
	fBeenIn65 		;55
	fBeenIn66 			;56
	fBeenIn67 			;57
	fBeenIn68 			;58
	fBeenIn69 			;59
	fBeenIn70 			;60 ;unused
	fBeenIn71 			;61
	fBeenIn72 			;62
	fBeenIn73 		;63 ;unused
	fBeenIn74 			;64
	fBeenIn75 			;65
	fBeenIn76 				;66
	fBeenIn77 			;67
	fBeenIn78 			;68
	fBeenIn79 			;69
	fBeenIn80 			;70
	fBeenIn81 			;71 ;log crossroads
	fBeenIn82 		;72
	fBeenIn83 		;73
	fBeenIn84 			;74
	fBeenIn85 			;75
	fBeenIn86 			;76
	fBeenIn87 				;77
	fBeenIn88		 			;78
	fBeenIn89		 			;79
	fBeenIn90		 			;80 ;unused
	fBeenIn91			 		;81
	fBeenIn92 					;82
	fBeenIn93			 		;83
	fBeenIn94				 	;84
	fBeenIn95 					;85 ;unused
	fBeenIn96 					;86
	fBeenIn97			 		;87
	fBeenIn300			 		;88
	fBeenIn301					;89
	fBeenIn310				 	;90
	fBeenIn311				 	;91
	fBeenIn313		 			;92
	fBeenIn314 	;93
	fBeenIn320		;94
	fBeenIn321 		;95
	fBeenIn322 				;96
	fBeenIn330 		;97
	fBeenIn331 		;98
	fBeenIn332 		;99
	fBeenIn333		 			;100
	fBeenIn334			 		;101
	fNextMonster				;102	;for Brigands and Goblins, check if any are still alive
	fUnused103					;103
	fUnused104					;104
	fUnused105					;105
	fUnused106					;106
	fUnused107					;107
	fUnused108					;108
	fUnused109					;109
)